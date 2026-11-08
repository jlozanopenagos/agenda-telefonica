package com.hackathon.agenda;

import com.hackathon.agenda.controller.PhoneBookController;
import com.hackathon.agenda.model.Contact;
import com.hackathon.agenda.model.PhoneBook;
import com.hackathon.agenda.service.PhoneBookService;
import com.hackathon.agenda.view.MainFrame;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PhoneBookService model = new PhoneBook();

            // 1. contactos predefinidos con teléfonos en formato válido
            model.addContact(new Contact("Carlos", "Santana", "600111222"));
            model.addContact(new Contact("Beatriz", "Pinzon", "+57 300 123 4567"));
            model.addContact(new Contact("Andres", "Cepeda", "600333444"));
            model.addContact(new Contact("Diana", "Uribe", "+34 654 987 321"));
            model.addContact(new Contact("Esteban", "Chaves", "600555666"));

            // 2. Instanciamos la Vista
            MainFrame view = new MainFrame();

            // 3. Conectamos ambos con el Controlador
            new PhoneBookController(model, view);

            // 4. Mostramos la ventana
            view.setVisible(true);
        });
    }
}