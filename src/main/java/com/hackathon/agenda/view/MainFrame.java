package com.hackathon.agenda.view;

import com.hackathon.agenda.utils.AppTheme;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {

    private final FormPanel formPanel;
    private final TablePanel tablePanel;
    private final StatusPanel statusPanel;

    public MainFrame() {
        setTitle("Agenda Telefónica MVC");
        setSize(850, 580);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        getContentPane().setBackground(AppTheme.BACKGROUND);

        // Inicializamos los 3 subpaneles
        formPanel = new FormPanel();
        tablePanel = new TablePanel();
        statusPanel = new StatusPanel();

        // Los agregamos a la ventana
        add(formPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);
    }

    public FormPanel getFormPanel() { return formPanel; }
    public TablePanel getTablePanel() { return tablePanel; }
    public StatusPanel getStatusPanel() { return statusPanel; }
}
