package src.main.java;

import java.time.LocalDate;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

public class Laptops extends LibraryItem{
    @CsvBindByName(column = "BRAND")
    private String brand;

    @CsvBindByName(column = "MODEL")
    private String model;

    @CsvBindByName(column = "OPERATINGSYSTEM")
    private String operatingSystem;

    @CsvBindByName(column = "RELEASEDATE")
    @CsvDate("yyyy-MM-dd")
    private LocalDate releaseDate;

    @CsvBindByName(column = "SERIALNUMBER")
    private String serialNumber;

    // unborrowed laptops
    public Laptops(){
        //empty constructor required for Java Beans
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        //inherits toString method from LibraryItem and adds the laptop specific fields
        return super.toString() + "Laptops [brand=" + brand + ", model=" + model + ", operatingSystem=" + operatingSystem + ", releaseDate=" + releaseDate + ", serialNumber=" + serialNumber + "]";
    }
}
