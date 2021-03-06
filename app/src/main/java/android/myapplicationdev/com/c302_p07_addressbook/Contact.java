package android.myapplicationdev.com.c302_p07_addressbook;

public class Contact {
    private String id;
    private String firstName;
    private String lastName;
    private String mobileNumber;

    public Contact(String id, String firstName, String lastName, String mobileNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
