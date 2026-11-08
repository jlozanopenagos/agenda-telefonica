package com.hackathon.agenda.view;

import com.hackathon.agenda.model.Contact;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TablePanel extends JPanel {

    // 1. Tabla principal (todos los contactos)
    private final DefaultTableModel tableModel;
    private final JTable tableContacts;

    // 2. Tabla secundaria (resultados de búsqueda)
    private final DefaultTableModel searchTableModel;
    private final JTable tableSearchResults;

    public TablePanel() {
        // Disposición en dos columnas de igual tamaño con 10px de separación
        setLayout(new GridLayout(1, 2, 10, 0));
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        String[] columns = {"Nombre", "Apellido", "Teléfono"};

        // Configuración tabla principal
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableContacts = new JTable(tableModel);
        tableContacts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollMain = new JScrollPane(tableContacts);
        scrollMain.setBorder(BorderFactory.createTitledBorder("Agenda Completa"));

        // Configuración tabla de búsqueda
        searchTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableSearchResults = new JTable(searchTableModel);
        tableSearchResults.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollSearch = new JScrollPane(tableSearchResults);
        scrollSearch.setBorder(BorderFactory.createTitledBorder("Resultados de Búsqueda"));

        add(scrollMain);
        add(scrollSearch);
    }

    // Métodos para la tabla principal
    public void updateTable(List<Contact> contacts) {
        tableModel.setRowCount(0);
        for (Contact c : contacts) {
            tableModel.addRow(new Object[]{
                    c.getFirstName(),
                    c.getLastName(),
                    c.getPhoneNumber()
            });
        }
    }

    // Métodos para la tabla de resultados de búsqueda
    public void updateSearchResults(List<Contact> results) {
        searchTableModel.setRowCount(0);
        for (Contact c : results) {
            searchTableModel.addRow(new Object[]{
                    c.getFirstName(),
                    c.getLastName(),
                    c.getPhoneNumber()
            });
        }
    }

    public void clearSearchResults() {
        searchTableModel.setRowCount(0);
        tableSearchResults.clearSelection();
    }

    public void clearSelection() {
        tableContacts.clearSelection();
        tableSearchResults.clearSelection();
    }

    public JTable getTableContacts() {
        return tableContacts;
    }

    public JTable getTableSearchResults() {
        return tableSearchResults;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public DefaultTableModel getSearchTableModel() {
        return searchTableModel;
    }
}