package com.hackathon.agenda.view;

import com.hackathon.agenda.model.Contact;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TablePanel extends JPanel {

    private final DefaultTableModel tableModel;
    private final JTable tableContacts;

    public TablePanel() {
        setLayout(new BorderLayout());

        String[] columns = {"Nombre", "Apellido", "Teléfono"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableContacts = new JTable(tableModel);
        tableContacts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tableContacts);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Contactos"));
        add(scrollPane, BorderLayout.CENTER);
    }

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

    public void clearSelection() {
        tableContacts.clearSelection();
    }

    public JTable getTableContacts() {
        return tableContacts;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
