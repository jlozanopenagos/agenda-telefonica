package com.hackathon.agenda.model;

import java.util.Objects;

public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Contact(String firstName, String lastName, String phoneNumber){
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío ni contener solo espacios.");
        }
        this.firstName = firstName.trim();

        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío ni contener solo espacios.");
        }
        this.lastName = lastName.trim();

        if(!isValidPhoneNumber(phoneNumber)){
            throw new IllegalArgumentException("El formato del teléfono no es válido (ej: 600000000 o +34 600 000 000)");
        }
        this.phoneNumber = phoneNumber.trim();
    }

    public static boolean isValidPhoneNumber(String phone) {

        String regex = "^(\\d{9}|\\+\\d{1,3}(?: ?\\d+)+)$";

        return phone != null && phone.matches(regex);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(!isValidPhoneNumber(phoneNumber)){
            throw new IllegalArgumentException("El formato del teléfono no es válido (ej: 600000000 o +34 600 000 000)");
        }
        this.phoneNumber = phoneNumber.trim();
    }

    // Check that firstName AND LastName are equals ignoring their case
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return firstName.equalsIgnoreCase(contact.firstName) &&
                lastName.equalsIgnoreCase(contact.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName.toLowerCase(), lastName.toLowerCase());
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + phoneNumber + ")";
    }
}
