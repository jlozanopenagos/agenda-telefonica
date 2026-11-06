package com.hackathon.agenda;

import com.hackathon.agenda.model.Contact;
import com.hackathon.agenda.model.PhoneBook;

public class Main {
    public static void main(String[] args){
        // Instancia de la agenda con capacidad por defecto (10 contactos)
        PhoneBook agenda = new PhoneBook();

        // 5 instancias de Contacto para pruebas
        Contact c1 = new Contact("Carlos", "Santana", "600111222");
        Contact c2 = new Contact("Ana", "Gomez", "+34 600 333 444");
        Contact c3 = new Contact("Beatriz", "Pinzon", "600555666");
        Contact c4 = new Contact("David", "Ospina", "+57 300 777 8888");
        Contact c5 = new Contact("carlos", "santana", "600999888");

        System.out.println("Instancias creadas correctamente:");
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
        int isFull = agenda.freeSpaces();
        System.out.println(isFull);
    }
}
