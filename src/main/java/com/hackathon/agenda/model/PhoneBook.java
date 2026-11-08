package com.hackathon.agenda.model;

import com.hackathon.agenda.service.PhoneBookService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PhoneBook implements PhoneBookService {
    private static final int DEFAULT_CAPACITY = 10;

    private final List<Contact> contacts;
    private final int capacity;

    // Constructor por defecto: invoca al sobrecargado pasando 10
    public PhoneBook() {
        this(DEFAULT_CAPACITY);
    }

    // Constructor sobrecargado: recibe el tamaño personalizado
    public PhoneBook(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("La capacidad máxima debe ser mayor a 0.");
        }
        this.capacity = capacity;
        this.contacts = new ArrayList<>();
    }

    @Override
    public boolean isFull(){
        return contacts.size() >= this.capacity;
    }

    @Override
    public int freeSpaces(){
        return this.capacity - contacts.size();
    }

    @Override
    public boolean hasContact(Contact c){
        if(c == null)
            return false;
        return this.contacts.contains(c);
    }

    @Override
    public void addContact(Contact c) {
        if (c == null) {
            throw new IllegalArgumentException("El contacto a agregar no puede ser nulo.");
        }
        if (isFull()) {
            throw new IllegalArgumentException("La agenda no tiene más espacio para guardar este contacto.");
        }
        if (hasContact(c)) {
            throw new IllegalArgumentException("Ese contacto ya existe.");
        }
        contacts.add(c);
    }

    @Override
    public String searchContact(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("El contacto a buscar no puede ser nulo.");
        }
        if (firstName.isBlank() || lastName.isBlank()) {
            throw new IllegalArgumentException("El nombre y apellido del contacto a buscar no pueden estar en blanco.");
        }

        for (Contact contact : contacts) {
            if (firstName.trim().equalsIgnoreCase(contact.getFirstName()) &&
                    lastName.trim().equalsIgnoreCase(contact.getLastName())) {
                return contact.getPhoneNumber();
            }
        }
        throw new NoSuchElementException("El contacto no se encontró en la agenda.");
    }

    @Override
    public boolean deleteContact(Contact c) {
        if (c == null) {
            throw new IllegalArgumentException("El contacto a eliminar no puede ser nulo.");
        }
        if (c.getFirstName().isBlank() || c.getLastName().isBlank()) {
            throw new IllegalArgumentException("El nombre y apellido del contacto no pueden estar en blanco.");
        }

        boolean removed = contacts.remove(c);
        if (!removed) {
            throw new NoSuchElementException("El contacto no existe en la agenda.");
        }
        return true;
    }

    @Override
    public boolean deleteContact(String firstName, String lastName) {
        if (firstName == null || lastName == null || firstName.isBlank() || lastName.isBlank()) {
            throw new IllegalArgumentException("Nombre y apellido obligatorios para eliminar.");
        }
        return contacts.removeIf(c ->
                c.getFirstName().equalsIgnoreCase(firstName) &&
                        c.getLastName().equalsIgnoreCase(lastName)
        );
    }

    @Override
    public void updatePhone(String firstName, String lastName, String newPhoneNumber) {
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("El nombre y apellido no pueden ser nulos.");
        }
        if (firstName.isBlank() || lastName.isBlank()) {
            throw new IllegalArgumentException("El nombre y apellido no pueden estar en blanco.");
        }

        for (Contact contact : contacts) {
            if (firstName.trim().equalsIgnoreCase(contact.getFirstName()) &&
                    lastName.trim().equalsIgnoreCase(contact.getLastName())) {
                contact.setPhoneNumber(newPhoneNumber); // Valida el formato automáticamente
                return;
            }
        }
        throw new NoSuchElementException("No se puede modificar: el contacto no existe.");
    }

    @Override
    public List<Contact> listContacts() {
        List<Contact> sortedList = new ArrayList<>(this.contacts);
        sortedList.sort((c1, c2) -> {
            int comp = c1.getFirstName().compareToIgnoreCase(c2.getFirstName());
            if (comp == 0) {
                return c1.getLastName().compareToIgnoreCase(c2.getLastName());
            }
            return comp;
        });
        return sortedList;
    }

    @Override
    public String toString() {
        return "Contactos agregados{" +
                "contactos= " + contacts +
                '}';
    }
}