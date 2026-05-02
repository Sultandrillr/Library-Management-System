package src.main.java;

import com.opencsv.bean.CsvBindByName;

public class Customer {
    @CsvBindByName(column = "ID")
    private int customerID;

    @CsvBindByName(column = "NAME")
    private String name;

    @CsvBindByName(column = "EMAIL")
    private String email;

    @CsvBindByName(column = "PHONENUMBER")
    private String phoneNumber;

    @CsvBindByName(column = "ADDRESS")
    private String address;

    @CsvBindByName(column = "FINES")
    private double fines;

    public Customer(){
        //empty constructor required for Java Beans
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int ID) {
        this.customerID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getFines() {
        return fines;
    }

    public void setFines(double fines) {
        this.fines = fines;
    }

    public String toString(){
        return String.format("id=%d,name=%s,Email=%s,PhoneNumber=%s,address=%s,fines=%.2f",customerID,name,email,phoneNumber,address,fines);
    }
}
