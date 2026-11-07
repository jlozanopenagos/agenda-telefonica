package com.hackathon.agenda.controller;

import com.hackathon.agenda.model.Contact;
import com.hackathon.agenda.service.PhoneBookService;
import com.hackathon.agenda.view.MainFrame;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import java.util.NoSuchElementException;

public class PhoneBookController {

    private final PhoneBookService model;
    private final MainFrame view;

    public PhoneBookController(PhoneBookService model, MainFrame view) {
        this.model = model;
        this.view = view;

        initListeners();
        refreshView();
        view.getStatusPanel().showMessage("Agenda lista.", true);
    }

    private void initListeners() {
        // Enlace de los botones a sus respectivos métodos manejadores
        view.getFormPanel().getBtnAdd().addActionListener(e -> handleAddContact());
        view.getFormPanel().getBtnSearch().addActionListener(e -> handleSearchContact());
        view.getFormPanel().getBtnUpdate().addActionListener(e -> handleUpdateContact());
        view.getFormPanel().getBtnDelete().addActionListener(e -> handleDeleteContact());
        view.getFormPanel().getBtnClear().addActionListener(e -> handleClearForm());

        // Escucha el evento de selección de filas en la tabla
        view.getTablePanel().getTableContacts().getSelectionModel().addListSelectionListener(this::handleTableSelection);
    }

    // Refresca la tabla ordenada y los espacios disponibles
    private void refreshView() {
        view.getTablePanel().updateTable(model.listContacts());
        view.getStatusPanel().updateFreeSpaces(model.freeSpaces());
    }

    // 1. Añadir contacto
    private void handleAddContact() {
        try {
            String firstName = view.getFormPanel().getFirstNameInput();
            String lastName = view.getFormPanel().getLastNameInput();
            String phone = view.getFormPanel().getPhoneInput();

            Contact newContact = new Contact(firstName, lastName, phone);
            model.addContact(newContact);

            refreshView();
            view.getFormPanel().clearForm();
            view.getStatusPanel().showMessage("Contacto agregado exitosamente.", true);
        } catch (IllegalArgumentException e) {
            view.getStatusPanel().showMessage(e.getMessage(), false);
        } catch (Exception e) {
            view.getStatusPanel().showMessage("Error al añadir: " + e.getMessage(), false);
        }
    }

    // 2. Buscar contacto por nombre y apellido
    private void handleSearchContact() {
        try {
            String firstName = view.getFormPanel().getFirstNameInput();
            String lastName = view.getFormPanel().getLastNameInput();

            String phoneFound = model.searchContact(firstName, lastName);
            view.getFormPanel().setFormValues(firstName, lastName, phoneFound);
            view.getStatusPanel().showMessage("Contacto encontrado.", true);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            view.getStatusPanel().showMessage(e.getMessage(), false);
        } catch (Exception e) {
            view.getStatusPanel().showMessage("Error al buscar: " + e.getMessage(), false);
        }
    }

    // 3. Modificar teléfono de un contacto existente
    private void handleUpdateContact() {
        try {
            String firstName = view.getFormPanel().getFirstNameInput();
            String lastName = view.getFormPanel().getLastNameInput();
            String newPhone = view.getFormPanel().getPhoneInput();

            model.updatePhone(firstName, lastName, newPhone);

            refreshView();
            view.getStatusPanel().showMessage("Teléfono actualizado correctamente.", true);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            view.getStatusPanel().showMessage(e.getMessage(), false);
        } catch (Exception e) {
            view.getStatusPanel().showMessage("Error al modificar: " + e.getMessage(), false);
        }
    }

    // 4. Eliminar contacto
    private void handleDeleteContact() {
        try {
            String firstName = view.getFormPanel().getFirstNameInput();
            String lastName = view.getFormPanel().getLastNameInput();
            String phone = view.getFormPanel().getPhoneInput();

            // Contacto de referencia para la comparación con equals()
            Contact contactToDelete = new Contact(firstName, lastName, phone.isEmpty() ? "600000000" : phone);

            model.deleteContact(contactToDelete);

            refreshView();
            view.getFormPanel().clearForm();
            view.getTablePanel().clearSelection();
            view.getStatusPanel().showMessage("Contacto eliminado exitosamente.", true);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            view.getStatusPanel().showMessage(e.getMessage(), false);
        } catch (Exception e) {
            view.getStatusPanel().showMessage("Error al eliminar: " + e.getMessage(), false);
        }
    }

    // 5. Limpiar formulario y selección visual
    private void handleClearForm() {
        view.getFormPanel().clearForm();
        view.getTablePanel().clearSelection();
        view.getStatusPanel().showMessage("Campos limpiados.", true);
    }

    // 6. Sincronización: transferir datos de la fila seleccionada al formulario
    private void handleTableSelection(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) {
            JTable table = view.getTablePanel().getTableContacts();
            int selectedRow = table.getSelectedRow();

            if (selectedRow != -1) {
                String firstName = String.valueOf(table.getValueAt(selectedRow, 0));
                String lastName = String.valueOf(table.getValueAt(selectedRow, 1));
                String phone = String.valueOf(table.getValueAt(selectedRow, 2));

                view.getFormPanel().setFormValues(firstName, lastName, phone);
            }
        }
    }
}