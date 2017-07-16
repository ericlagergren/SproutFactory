package models;

import utils.UUID;

public class Supplier {
    public UUID id;
    public String firstName;
    public String lastName;
    public String email;

    public Supplier(String firstName, String lastName, String email) {
        this(null, firstName, lastName, email);
    }

    public Supplier(UUID id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Supplier supplier = (Supplier) o;

        if (id != null ? !id.equals(supplier.id) : supplier.id != null) return false;
        if (firstName != null ? !firstName.equals(supplier.firstName) : supplier.firstName != null) return false;
        if (lastName != null ? !lastName.equals(supplier.lastName) : supplier.lastName != null) return false;
        return email != null ? email.equals(supplier.email) : supplier.email == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}