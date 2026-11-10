package com.hackathon.agenda.view;

import com.hackathon.agenda.utils.AppTheme;

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
        setLayout(new GridBagLayout());
        setBackground(AppTheme.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Dimensiones fijas y proporcionales para evitar colapsos
        Dimension fieldDimension = new Dimension(260, 32);
        Dimension buttonDimension = new Dimension(115, 32);

        styleTextField(txtFirstName, fieldDimension);
        styleTextField(txtLastName, fieldDimension);
        styleTextField(txtPhone, fieldDimension);

        JLabel lblFirstName = new JLabel("Nombre:");
        JLabel lblLastName = new JLabel("Apellido:");
        JLabel lblPhone = new JLabel("Teléfono:");

        styleLabel(lblFirstName);
        styleLabel(lblLastName);
        styleLabel(lblPhone);

        styleButton(btnAdd, AppTheme.PRIMARY, Color.WHITE, buttonDimension);
        styleButton(btnSearch, new Color(79, 70, 229), Color.WHITE, buttonDimension);
        styleButton(btnUpdate, new Color(217, 119, 6), Color.WHITE, buttonDimension);
        styleButton(btnDelete, AppTheme.DANGER, Color.WHITE, buttonDimension);
        styleButton(btnClear, new Color(107, 114, 128), Color.WHITE, buttonDimension);

        // -------------------------------------------------------------
        // COLUMNA IZQUIERDA: Formulario (Labels + TextFields)
        // -------------------------------------------------------------
        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        fieldsPanel.setBackground(AppTheme.BACKGROUND);
        GridBagConstraints gbcF = new GridBagConstraints();
        gbcF.insets = new Insets(6, 6, 6, 6);
        gbcF.anchor = GridBagConstraints.WEST;

        // Fila 0
        gbcF.gridx = 0; gbcF.gridy = 0;
        fieldsPanel.add(lblFirstName, gbcF);
        gbcF.gridx = 1; gbcF.gridy = 0;
        fieldsPanel.add(txtFirstName, gbcF);

        // Fila 1
        gbcF.gridx = 0; gbcF.gridy = 1;
        fieldsPanel.add(lblLastName, gbcF);
        gbcF.gridx = 1; gbcF.gridy = 1;
        fieldsPanel.add(txtLastName, gbcF);

        // Fila 2
        gbcF.gridx = 0; gbcF.gridy = 2;
        fieldsPanel.add(lblPhone, gbcF);
        gbcF.gridx = 1; gbcF.gridy = 2;
        fieldsPanel.add(txtPhone, gbcF);

        // -------------------------------------------------------------
        // COLUMNA DERECHA: Botones (2, 2 y 1 centrado)
        // -------------------------------------------------------------
        JPanel buttonsGrid = new JPanel(new GridBagLayout());
        buttonsGrid.setBackground(AppTheme.BACKGROUND);
        GridBagConstraints gbcB = new GridBagConstraints();
        gbcB.insets = new Insets(5, 5, 5, 5);

        // Fila 1 de botones: Añadir y Buscar (2)
        gbcB.gridx = 0; gbcB.gridy = 0;
        buttonsGrid.add(btnAdd, gbcB);
        gbcB.gridx = 1; gbcB.gridy = 0;
        buttonsGrid.add(btnSearch, gbcB);

        // Fila 2 de botones: Modificar y Eliminar (2)
        gbcB.gridx = 0; gbcB.gridy = 1;
        buttonsGrid.add(btnUpdate, gbcB);
        gbcB.gridx = 1; gbcB.gridy = 1;
        buttonsGrid.add(btnDelete, gbcB);

        // Fila 3 de botones: Limpiar centrado abarcando ambas columnas (1)
        gbcB.gridx = 0; gbcB.gridy = 2;
        gbcB.gridwidth = 2;
        gbcB.anchor = GridBagConstraints.CENTER;
        buttonsGrid.add(btnClear, gbcB);

        // -------------------------------------------------------------
        // MONTAJE GENERAL DEL FORM PANEL
        // -------------------------------------------------------------
        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.insets = new Insets(0, 15, 0, 15);
        mainGbc.fill = GridBagConstraints.NONE;
        mainGbc.anchor = GridBagConstraints.CENTER;

        mainGbc.gridx = 0; mainGbc.gridy = 0;
        add(fieldsPanel, mainGbc);

        mainGbc.gridx = 1; mainGbc.gridy = 0;
        add(buttonsGrid, mainGbc);
    }

    private void styleLabel(JLabel label) {
        label.setFont(AppTheme.BOLD_FONT);
        label.setForeground(AppTheme.TEXT_DARK);
        label.setPreferredSize(new Dimension(75, 26));
    }

    private void styleTextField(JTextField textField, Dimension dim) {
        textField.setFont(AppTheme.MAIN_FONT);
        textField.setForeground(AppTheme.TEXT_DARK);
        textField.setBackground(Color.WHITE);
        textField.setPreferredSize(dim);
        textField.setMinimumSize(dim);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225), 1, true),
                BorderFactory.createEmptyBorder(4, 10, 4, 10)
        ));
    }

    private void styleButton(JButton button, Color bgColor, Color textColor, Dimension dim) {
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFont(AppTheme.BOLD_FONT);
        button.setFocusPainted(false);
        button.setPreferredSize(dim);
        button.setMinimumSize(dim);
        button.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
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