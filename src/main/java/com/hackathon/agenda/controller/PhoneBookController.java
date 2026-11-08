package com.hackathon.agenda.controller;

import com.hackathon.agenda.model.Contact;
import com.hackathon.agenda.service.PhoneBookService;
import com.hackathon.agenda.view.MainFrame;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import java.util.List;
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

        // Listener de selección en tabla principal
        view.getTablePanel().getTableContacts().getSelectionModel()
                .addListSelectionListener(e -> handleTableSelection(e, view.getTablePanel().getTableContacts()));

        // Listener de selección en tabla de resultados de búsqueda
        view.getTablePanel().getTableSearchResults().getSelectionModel()
                .addListSelectionListener(e -> handleTableSelection(e, view.getTablePanel().getTableSearchResults()));
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
            view.getTablePanel().clearSelection();
            view.getStatusPanel().showMessage("Contacto agregado exitosamente.", true);
        } catch (IllegalArgumentException e) {
            view.getStatusPanel().showMessage(e.getMessage(), false);
        } catch (Exception e) {
            view.getStatusPanel().showMessage("Error al añadir: " + e.getMessage(), false);
        }
    }

    // 2. Buscar contacto por nombre y apellido O por numero de telefono
    private void handleSearchContact() {
        try {
            String firstName = view.getFormPanel().getFirstNameInput();
            String lastName = view.getFormPanel().getLastNameInput();
            String phone = view.getFormPanel().getPhoneInput();

            // 1. Búsqueda por número o fragmento de teléfono
            if (firstName.isBlank() && lastName.isBlank() && !phone.isBlank()) {
                List<Contact> matches = model.searchByPhone(phone);
                view.getTablePanel().updateSearchResults(matches);
                view.getStatusPanel().showMessage("Se encontraron " + matches.size() + " coincidencia(s).", true);
                return;
            }

            // 2. Búsqueda exacta por Nombre y Apellido
            if (!firstName.isBlank() && !lastName.isBlank()) {
                String phoneFound = model.searchContact(firstName, lastName);
                Contact found = new Contact(firstName, lastName, phoneFound);

                // Muestra la coincidencia única en la tabla de resultados y en los campos
                view.getTablePanel().updateSearchResults(List.of(found));
                view.getFormPanel().setFormValues(firstName, lastName, phoneFound);
                view.getStatusPanel().showMessage("Contacto localizado con éxito.", true);
                return;
            }

            // 3. Si todo está vacío, limpia la tabla de búsqueda
            if (firstName.isBlank() && lastName.isBlank() && phone.isBlank()) {
                view.getTablePanel().clearSearchResults();
                view.getStatusPanel().showMessage("Búsqueda restablecida.", true);
                return;
            }

            throw new IllegalArgumentException("Ingresa Nombre y Apellido, o escribe un número en Teléfono para buscar.");

        } catch (IllegalArgumentException | NoSuchElementException e) {
            view.getTablePanel().clearSearchResults();
            view.getStatusPanel().showMessage(e.getMessage(), false);
        } catch (Exception e) {
            view.getTablePanel().clearSearchResults();
            view.getStatusPanel().showMessage("Error durante la búsqueda: " + e.getMessage(), false);
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
            view.getStatusPanel().showMessage("Teléfono modificado exitosamente.", true);
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

            model.deleteContact(firstName, lastName);

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
        view.getTablePanel().clearSearchResults(); // Limpia la tabla de búsqueda
        view.getStatusPanel().showMessage("Formulario y resultados limpiados.", true);
    }

    // 6. Sincronización: transferir datos de la fila seleccionada al formulario
    private void handleTableSelection(ListSelectionEvent event, JTable sourceTable) {
        if (!event.getValueIsAdjusting()) {
            int selectedRow = sourceTable.getSelectedRow();
            if (selectedRow != -1) {
                String firstName = String.valueOf(sourceTable.getValueAt(selectedRow, 0));
                String lastName = String.valueOf(sourceTable.getValueAt(selectedRow, 1));
                String phone = String.valueOf(sourceTable.getValueAt(selectedRow, 2));

                view.getFormPanel().setFormValues(firstName, lastName, phone);
            }
        }
    }
}