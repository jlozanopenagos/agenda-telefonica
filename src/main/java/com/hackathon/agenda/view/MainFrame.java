package com.hackathon.agenda.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class MainFrame extends JFrame {
    // 1. Entradas de texto
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtPhone;

    // 2. Botones de acción
    private JButton btnAdd;
    private JButton btnSearch;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;

    // 3. Tabla y su modelo de datos
    private JTable tableContacts;
    private DefaultTableModel tableModel;

    // 4. Panel de estado y feedback visual
    private JLabel lblStatus;
    private JLabel lblFreeSpaces;

    public MainFrame() {
        // Configuración básica de la ventana
        setTitle("Agenda Telefónica MVC");
        setSize(750, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10)); // Espaciado horizontal y vertical de 10px

        // Inicializamos los paneles
        initFormPanel();
        initTablePanel();
        initStatusPanel();
    }

    private void initStatusPanel() {
    }

    private void initTablePanel() {
    }

    private void initFormPanel() {
        JPanel formContainer = new JPanel(new BorderLayout(5, 5));
        formContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        // Cuadrícula para Etiquetas y Campos de texto (3 filas, 2 columnas)
        JPanel fieldsPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        fieldsPanel.add(new JLabel("Nombre:"));
        txtFirstName = new JTextField();
        fieldsPanel.add(txtFirstName);

        fieldsPanel.add(new JLabel("Apellido:"));
        txtLastName = new JTextField();
        fieldsPanel.add(txtLastName);

        fieldsPanel.add(new JLabel("Teléfono:"));
        txtPhone = new JTextField();
        fieldsPanel.add(txtPhone);

        // Panel de Botones (uno al lado del otro)
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnAdd = new JButton("Añadir");
        btnSearch = new JButton("Buscar");
        btnUpdate = new JButton("Modificar");
        btnDelete = new JButton("Eliminar");
        btnClear = new JButton("Limpiar");

        buttonsPanel.add(btnAdd);
        buttonsPanel.add(btnSearch);
        buttonsPanel.add(btnUpdate);
        buttonsPanel.add(btnDelete);
        buttonsPanel.add(btnClear);

        // Ensamblar en el contenedor superior y añadirlo al NORTE de la ventana
        formContainer.add(fieldsPanel, BorderLayout.CENTER);
        formContainer.add(buttonsPanel, BorderLayout.SOUTH);
        add(formContainer, BorderLayout.NORTH);
    }
}
