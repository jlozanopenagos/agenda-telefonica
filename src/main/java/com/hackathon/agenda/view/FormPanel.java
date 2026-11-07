package com.hackathon.agenda.view;

import javax.swing.*;
import java.awt.*;

public class FormPanel extends JPanel {

    private final JTextField txtFirstName = new JTextField();
    private final JTextField txtLastName = new JTextField();
    private final JTextField txtPhone = new JTextField();

    private final JButton btnAdd = new JButton("Añadir");
    private final JButton btnSearch = new JButton("Buscar");
    private final JButton btnUpdate = new JButton("Modificar");
    private final JButton btnDelete = new JButton("Eliminar");
    private final JButton btnClear = new JButton("Limpiar");

    public FormPanel() {
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        JPanel fieldsPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        fieldsPanel.add(new JLabel("Nombre:"));
        fieldsPanel.add(txtFirstName);
        fieldsPanel.add(new JLabel("Apellido:"));
        fieldsPanel.add(txtLastName);
        fieldsPanel.add(new JLabel("Teléfono:"));
        fieldsPanel.add(txtPhone);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonsPanel.add(btnAdd);
        buttonsPanel.add(btnSearch);
        buttonsPanel.add(btnUpdate);
        buttonsPanel.add(btnDelete);
        buttonsPanel.add(btnClear);

        add(fieldsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    // Métodos específicos del formulario
    public String getFirstNameInput() { return txtFirstName.getText().trim(); }
    public String getLastNameInput() { return txtLastName.getText().trim(); }
    public String getPhoneInput() { return txtPhone.getText().trim(); }

    public void setFormValues(String firstName, String lastName, String phone) {
        txtFirstName.setText(firstName);
        txtLastName.setText(lastName);
        txtPhone.setText(phone);
    }

    public void clearForm() {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtPhone.setText("");
    }

    // Getters de los botones para el controlador
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnSearch() { return btnSearch; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnClear() { return btnClear; }
}