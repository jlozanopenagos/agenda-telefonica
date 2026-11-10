package com.hackathon.agenda.view;

import com.hackathon.agenda.model.Contact;
import com.hackathon.agenda.utils.AppTheme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
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
        setLayout(new GridLayout(1, 2, 12, 0));
        setBackground(AppTheme.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        String[] columns = {"Nombre", "Apellido", "Teléfono"};

        // 1. Tabla Principal
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tableContacts = new JTable(tableModel);
        styleTable(tableContacts);
        JScrollPane scrollMain = new JScrollPane(tableContacts);
        scrollMain.setBackground(AppTheme.BACKGROUND);
        scrollMain.getViewport().setBackground(Color.WHITE);
        scrollMain.setBorder(BorderFactory.createTitledBorder("Agenda Completa"));

        // 2. Tabla Secundaria (Búsqueda)
        searchTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tableSearchResults = new JTable(searchTableModel);
        styleTable(tableSearchResults);
        JScrollPane scrollSearch = new JScrollPane(tableSearchResults);
        scrollSearch.setBackground(AppTheme.BACKGROUND);
        scrollSearch.getViewport().setBackground(Color.WHITE);
        scrollSearch.setBorder(BorderFactory.createTitledBorder("Resultados de Búsqueda"));

        add(scrollMain);
        add(scrollSearch);
    }

    private void styleTable(JTable table) {
        table.setFont(AppTheme.MAIN_FONT);
        table.setForeground(AppTheme.TEXT_DARK);
        table.setRowHeight(26);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 2));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setSelectionBackground(new Color(219, 234, 254)); // Azul suave
        table.setSelectionForeground(AppTheme.TEXT_DARK);

        JTableHeader header = table.getTableHeader();
        header.setFont(AppTheme.BOLD_FONT);
        header.setBackground(new Color(241, 245, 249));
        header.setForeground(AppTheme.TEXT_DARK);
        header.setPreferredSize(new Dimension(0, 30));
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