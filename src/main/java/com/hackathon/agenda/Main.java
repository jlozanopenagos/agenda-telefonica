package com.hackathon.agenda;

import com.hackathon.agenda.controller.PhoneBookController;
import com.hackathon.agenda.model.PhoneBook;
import com.hackathon.agenda.view.MainFrame;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1. Instanciamos el Modelo
            PhoneBook model = new PhoneBook();

            // 2. Instanciamos la Vista
            MainFrame view = new MainFrame();

            // 3. Conectamos ambos con el Controlador
            new PhoneBookController(model, view);

            // 4. Mostramos la ventana
            view.setVisible(true);
        });
    }
}