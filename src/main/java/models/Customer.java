package models;

import java.util.Date;

public class Customer {
    public int id;
    public String firstName;
    public String lastName;
    public String email;
    public Date birthDate;


    public Customer(String firstName, String lastName, String email, Date birthDate) {
        this(0, firstName, lastName, email, birthDate);
    }

    public Customer(int id, String firstName, String lastName, String email, Date birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer c = (Customer) o;

        if (id != c.id) return false;
        if (firstName != null ? !firstName.equals(c.firstName) : c.firstName != null) return false;
        if (lastName != null ? !lastName.equals(c.lastName) : c.lastName != null) return false;
        return (email != null ? email.equals(c.email) : c.email == null) && (birthDate != null ? birthDate.equals(c.birthDate) : c.birthDate == null);
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(id);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }
}