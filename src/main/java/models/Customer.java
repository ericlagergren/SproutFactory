package models;

public class Customer {
    public String customerID;
    public String firstName;
    public String lastName;
    public String email;
    public String birthDate;

    public Customer() {}

    public Customer(String customerID, String firstName, String lastName, String email, String birthDate) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (customerID != null ? !customerID.equals(customer.customerID) : customer.customerID != null) return false;
        if (firstName != null ? !firstName.equals(customer.firstName) : customer.firstName != null) return false;
        if (lastName != null ? !lastName.equals(customer.lastName) : customer.lastName != null) return false;
        return (email != null ? email.equals(customer.email) : customer.email == null) && (birthDate != null ? birthDate.equals(customer.birthDate) : customer.birthDate == null);
    }

    @Override
    public int hashCode() {
        int result = customerID != null ? customerID.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID='" + customerID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }
}