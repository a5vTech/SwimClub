public class Employee {
    //Fields
    protected String firstName;
    protected String lastName;
    protected String title;
    protected int phoneNumber;
    protected String address;


    //Overloaded constructors
    public Employee(String title, String firstName, String lastName, String address, int phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }


    //Getters
    public String getName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }
    //Setters

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String toString() {
        return String.format("%s;%s;%s;%s;%d", title, firstName, lastName, address, phoneNumber);
    }
}
