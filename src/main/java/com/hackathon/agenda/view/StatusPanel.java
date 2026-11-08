package com.hackathon.agenda.view;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {

    private final JLabel lblStatus = new JLabel("Listo.");
    private final JLabel lblFreeSpaces = new JLabel("Espacios libres: -");

    public StatusPanel() {
        setLayout(new BorderLayout(10, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 12, 10, 12));

        lblStatus.setFont(lblStatus.getFont().deriveFont(Font.BOLD));

        add(lblStatus, BorderLayout.WEST);
        add(lblFreeSpaces, BorderLayout.EAST);
    }

    public void showMessage(String message, boolean isSuccess) {
        lblStatus.setText(message);
        lblStatus.setForeground(isSuccess ? new Color(0, 140, 0) : Color.RED);
    }

    public void updateFreeSpaces(int spaces) {
        lblFreeSpaces.setText("Espacios libres: " + spaces);
    }
}