package src.main.java;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class LibraryItem{
    @CsvBindByName(column = "ID")
    private int id;

    @CsvBindByName(column = "TYPE")
    private String type;

    @CsvBindByName(column = "AVAILABLE")
    private boolean available = false; //default value for unloaned books

    @CsvBindByName(column = "BORROWERID")
    private Integer borrowerID = null; //default value for unloaned books

    @CsvBindByName(column = "BORROWDATE")
    @CsvDate("yyyy-MM-dd")
    private LocalDate  borrowDate = null; //default value for unloaned books

    static List<LibraryItem> allItems = new ArrayList<>();//allItems is a static list of all items in the library

    public void LibraryItem(){
        //empty constructor required for Java Beans
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Integer getBorrowerID() {
        return borrowerID;
    }
    public void setBorrowerID(Integer borrowerID) {
        this.borrowerID = borrowerID;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }
    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
        //Do this later
        //updateFines(this.fineAmount)
    }

    @Override
    public String toString() {
        return String.format("%s[id=%d,type=%s,Available=%s,BorrowerID=%d,BorrowedDate=%s]", this.getClass().getSimpleName(), id, type, available, borrowerID, borrowDate);
    }
}