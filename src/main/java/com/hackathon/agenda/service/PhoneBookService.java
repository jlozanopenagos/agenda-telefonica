package com.hackathon.agenda.service;

import com.hackathon.agenda.model.Contact;

import java.util.List;

public interface PhoneBookService {
    void addContact(Contact c);
    boolean hasContact(Contact c);
    List<Contact> listContacts();
    String searchContact(String firstName, String lastName);
    boolean deleteContact(Contact c);
    boolean deleteContact(String firstName, String lastName);
    void updatePhone(String firstName, String lastName, String newPhone);
    boolean isFull();
    int freeSpaces();
}