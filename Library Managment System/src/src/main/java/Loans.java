package src.main.java;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import java.time.LocalDate;

public class Loans{

    @CsvBindByName(column = "LOANID")
    private int loanID;

    @CsvBindByName(column = "CUSTOMERID")
    private int customerID;

    @CsvBindByName(column = "ITEMID")
    private int itemID;

    @CsvBindByName(column = "LOANDATE")
    @CsvDate("yyyy-MM-dd")
    private LocalDate loanDate;

    @CsvBindByName(column = "RETURNDATEe")
    @CsvDate("yyyy-MM-dd")
    private LocalDate returnDate;

    @CsvBindByName(column = "FINE")
    private double fineAmount;

    public Loans() {
        //empty constructor required for Java Beans
    }

    public int getLoanID() {
        return loanID;
    }
    public void setLoanID(int loanID) {
        this.loanID = loanID;
    }

    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getItemID() {
        return itemID;
    }
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }
    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getFineAmount() {
        return fineAmount;
    }
    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    public String toString() {
        return String.format("%s[id=%d,customerID=%d,itemID=%d,itemType=%s,loanDate=%s,returnDate=%s,fineAmount=%f]", this.getClass().getSimpleName(), loanID, customerID, itemID, loanDate, returnDate, fineAmount);
    }
}