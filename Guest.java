package P1;

public class Guest {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public Guest(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isSameGuest(Guest guest) {
        return this.firstName.equals(guest.getFirstName()) &&
                this.lastName.equals(guest.getLastName()) &&
                this.email.equals(guest.getEmail()) &&
                this.phoneNumber.equals(guest.getPhoneNumber());
    }

    @Override
    public String toString() {
        return String.format("%s %s",this.firstName, this.lastName);
    }

}
