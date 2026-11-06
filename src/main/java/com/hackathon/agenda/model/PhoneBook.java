package com.hackathon.agenda.model;

import java.util.ArrayList;
import java.util.List;

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

    //addContact(Contact c){}

    //searchContact(String firstName, String lastName){}

    //deleteContact(Contact c){}

    //updatePhone(String firstName, String lastName, String phoneNumber){}

    //listContacts(){]
}
