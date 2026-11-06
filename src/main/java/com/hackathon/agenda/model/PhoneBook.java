package com.hackathon.agenda.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class PhoneBook {
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

    public boolean isFull(){
        return contacts.size() >= this.capacity;
    }

    public int freeSpaces(){
        return this.capacity - contacts.size();
    }

    public boolean hasContact(Contact c){
        if(c == null)
            return false;
        return this.contacts.contains(c);
    }

    public void addContact(Contact c){
        if(c == null)
            throw new IllegalArgumentException("El contacto a agregar no puede estar vacio");

        if(isFull())
            throw new IllegalArgumentException("La agenda no tiene más espacio para guardar este contacto!");

        if(hasContact(c))
            throw new IllegalArgumentException("Ese contacto ya existe!");

        contacts.add(c);
    }

    public String searchContact(String firstName, String lastName){
        if( firstName == null || lastName == null)
            throw new IllegalArgumentException("El contacto a buscar no puede ser nullo");
        if(firstName.isBlank() || lastName.isBlank())
            throw new IllegalArgumentException("El nombre y apellido del contacto a buscar no puede estar en blanco");

        for(Contact contact:contacts){
            if(firstName.equalsIgnoreCase(contact.getFirstName()) && lastName.equalsIgnoreCase(contact.getLastName())){
                return contact.getPhoneNumber();
            }
        }
        throw new NoSuchElementException("El contacto no se encontro en la agenda");
    }

    public boolean deleteContact(Contact c){
        if( c == null)
            throw new IllegalArgumentException("El contacto a buscar no puede ser nullo");

        if(c.getFirstName().isBlank() || c.getLastName().isBlank())
            throw new IllegalArgumentException("El nombre y apellido del contacto a buscar no puede estar en blanco o ser nullo");

        boolean removed = contacts.remove(c);

        if(!removed)
            throw new NoSuchElementException("El contacto no existe en la agenda!");

        return true;
    }

    public void updatePhone(String firstName, String lastName, String newPhoneNumber) {
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("El nombre y apellido no pueden ser nulos.");
        }
        if (firstName.isBlank() || lastName.isBlank()) {
            throw new IllegalArgumentException("El nombre y apellido no pueden estar en blanco.");
        }

        for (Contact contact : contacts) {
            if (firstName.equalsIgnoreCase(contact.getFirstName()) && lastName.equalsIgnoreCase(contact.getLastName())) {
                contact.setPhoneNumber(newPhoneNumber); // El setter valida el Regex automáticamente
                return;
            }
        }
        throw new NoSuchElementException("No se puede modificar: el contacto no existe.");
    }

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