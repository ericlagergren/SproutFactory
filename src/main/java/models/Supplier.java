package models;

public class Supplier {
    private String suppliersID;
    private String firstName;
    private String lastName;
    private String suppliersEmail;


    public Supplier() {
    }

    public Supplier(String suppliersID, String firstName, String lastName, String suppliersEmail) {
        this.suppliersID = suppliersID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.suppliersEmail = suppliersEmail;
    }

    public String getSuppliersID() {
        return suppliersID;
    }

    public void setSuppliersID(String suppliersID) {
        this.suppliersID = suppliersID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSuppliersEmail() {
        return suppliersEmail;
    }

    public void setSuppliersEmail(String suppliersEmail) {
        this.suppliersEmail = suppliersEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Supplier supplier = (Supplier) o;

        if (suppliersID != null ? !suppliersID.equals(supplier.suppliersID) : supplier.suppliersID != null) return false;
        if (firstName != null ? !firstName.equals(supplier.firstName) : supplier.firstName != null) return false;
        if (lastName != null ? !lastName.equals(supplier.lastName) : supplier.lastName != null) return false;
        return (suppliersEmail != null ? suppliersEmail.equals(supplier.suppliersEmail) : supplier.suppliersEmail == null);
    }

    @Override
    public int hashCode() {
        int result = suppliersID != null ? suppliersID.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (suppliersEmail != null ? suppliersEmail.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "suppliersID='" + suppliersID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", suppliersEmail='" + suppliersEmail + '\'' +
                '}';
    }
}