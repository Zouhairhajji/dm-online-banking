package fr.esipe.creteil.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author HAJJI Zouhair
 */

public class User {

    @Size(min = 2, max = 20)
    @NotNull
    @NotBlank
    @NotEmpty
    private String firstName;

    
    
    @NotNull
    @Size(min = 2, max = 20)
    @NotEmpty
    private String lastName;

    public User() {
        this("none", "none");
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return "User{" + "firstName=" + firstName + ", lastName=" + lastName + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.firstName != null ? this.firstName.hashCode() : 0);
        hash = 67 * hash + (this.lastName != null ? this.lastName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if ((this.firstName == null) ? (other.firstName != null) : !this.firstName.equals(other.firstName)) {
            return false;
        }
        return !((this.lastName == null) ? (other.lastName != null) : !this.lastName.equals(other.lastName));
    }

    
    
    
    
    
}
