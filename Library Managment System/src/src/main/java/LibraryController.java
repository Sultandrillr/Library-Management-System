package src.main.java;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class LibraryController {
    List<Customer> customers = new ArrayList<>();//list of customers and values
    List<Loans> loans = new ArrayList<>();//list of loans and values
    List<Books> books = new ArrayList<>();//list of books and values
    List<Laptops> laptops = new ArrayList<>();//list of laptops and values
    List<CDs> cds = new ArrayList<>();//list of cds and values

    /**Load data from csv files into lists for use in the program
     *
     * @throws FileNotFoundException File not found
     */

    public void loadData() throws FileNotFoundException {
        Reader customersFile = new FileReader("customers.csv");
        customers = ReadCSV.readCSV(customersFile, Customer.class);

        Reader loansFile = new FileReader("loans.csv");
        loans = ReadCSV.readCSV(loansFile, Loans.class);

        Reader booksFile = new FileReader("books.csv");
        books = ReadCSV.readCSV(booksFile, Books.class);

        Reader laptopsFile = new FileReader("laptops.csv");
        laptops = ReadCSV.readCSV(laptopsFile, Laptops.class);

        Reader cdsFile = new FileReader("cds.csv");
        cds = ReadCSV.readCSV(cdsFile, CDs.class);

        //adds all items to the allItems list
        LibraryItem.allItems.addAll(books);
        LibraryItem.allItems.addAll(laptops);
        LibraryItem.allItems.addAll(cds);
    }

    /**
     * takes user input from menu and adds a new book to the list of books
     * @param name Title of book
     * @param author author of book
     * @param publisher publisher of book
     * @param ISBN
     * @param series
     * @param rating
     * @param description
     * @param language
     * @param genre
     * @param releaseDate
     */
    public void addBook(String name, String author, String publisher, String ISBN, String series, double rating,String description, String language, String genre, LocalDate releaseDate) {
        int id = nextHighestNumber();
        String type = "Book";
        
        Books newBook = new Books();
        newBook.setId(id);
        newBook.setType(type);
        newBook.setTitle(name);
        newBook.setAuthor(author);
        newBook.setPublisher(publisher);
        newBook.setISBN(ISBN);
        newBook.setSeries(series);
        newBook.setRating(rating);
        newBook.setDescription(description);
        newBook.setLanguage(language);
        newBook.setGenre(genre);
        newBook.setReleaseDate(releaseDate);
        newBook.setAvailable(true);

        books.add(newBook);
        LibraryItem.allItems.add(newBook);
        System.out.println("Book added successfully! New book id:" + id);

    }

    /**
     * removes a book from the list of books
     * @param id The Id  that is used to iterate through the list
     */

    public void removeBook(int id) {

        Books bookToRemove = null;
        for (Books book : books) {
            if (book.getId() == id) {
                bookToRemove = book;
                break;
            }
        }
        if (bookToRemove != null) {
            books.remove(bookToRemove);
            LibraryItem.allItems.remove(bookToRemove);
            System.out.println("Book removed successfully!");
        } else {
            System.out.println("Book not found!");
        }

    }

    /**
     * sets a value of a book in the list of books
     * @param id the id that is used to iterate through the list
     * @param field The name of the field what is being edited
     * @param newValue the new value that will take the place of the old one
     */
    public void setBookValue(int id, String field, String newValue) {
        Books bookToEdit = null;
        for (Books book : books) {
            if (book.getId() == id) {
                bookToEdit = book;
                break;
            }
        }
        if (bookToEdit == null) {
            System.out.println("Book not found!");
            return;
        }

        switch (field.toLowerCase()) {
            case "title":
            case "name":
                bookToEdit.setTitle(newValue);
                break;
            case "author":
                bookToEdit.setAuthor(newValue);
                break;
            case "publisher":
                bookToEdit.setPublisher(newValue);
                break;
            case "isbn":
                bookToEdit.setISBN(newValue);
                break;
            case "series":
                bookToEdit.setSeries(newValue);
                break;
            case "rating":
                try { bookToEdit.setRating(Float.parseFloat(newValue)); } catch (NumberFormatException e) { System.out.println("Invalid rating value"); return; }
                break;
            case "description":
                bookToEdit.setDescription(newValue);
                break;
            case "language":
                bookToEdit.setLanguage(newValue);
                break;
            case "genre":
                bookToEdit.setGenre(newValue);
                break;
            case "release date":
            case "date":
                try { bookToEdit.setReleaseDate(LocalDate.parse(newValue)); } catch (Exception e) { System.out.println("Invalid date format (yyyy-MM-dd)"); return; }
                break;
            default:
                System.out.println("Invalid field. Available fields: title, author, publisher, isbn, series, rating, description, language, genre, release date");
                return;
        }
        System.out.println("Book updated successfully!");
    }

    /**
     * loans a book to a customer
     * @param bookID the ID used to iterate through the list
     * @param customerID Id of customer who is borrowing book
     */
    public void loanBook(int bookID, int customerID) {
        Books bookToLoan = null;
        for (Books book : books) {
            if (book.getId() == bookID) {
                bookToLoan = book;
                break;
            }
        }
        if (bookToLoan == null) {
            System.out.println("Book not found!");
            return;

        }
        if(!bookToLoan.isAvailable()) {
            System.out.println("Book is already currently on loan.");
            return;
        }
        bookToLoan.setAvailable(false);
        Loans newLoan = new Loans();
        newLoan.setLoanID(nextHighestNumber());
        newLoan.setCustomerID(customerID);
        newLoan.setItemID(bookID);
        newLoan.setLoanDate(LocalDate.now());
        newLoan.setReturnDate(null);
        loans.add(newLoan);
    }

    /**
     * processes user returning a book
     * @param id ID used to iterate through list
     */
    public void returnBook(int id) {

        Books bookToReturn = null;
        for (Books book : books) {
            if (book.getId() == id) {
                bookToReturn = book;
                break;
            }
        }
        if (bookToReturn != null) {
            bookToReturn.setAvailable(true);
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Book not found!");
        }
    }

    /**
     * adds a laptop to the list of laptops
     * @param brand
     * @param model
     * @param os
     * @param serialNumber
     * @param releaseDate
     */
    public void addLaptop(String brand, String model, String os, String serialNumber, LocalDate releaseDate) {
        int id = nextHighestNumber();
        String type = "Laptop";

        Laptops newLaptop = new Laptops();
        newLaptop.setId(id);
        newLaptop.setType(type);
        newLaptop.setBrand(brand);
        newLaptop.setModel(model);
        newLaptop.setOperatingSystem(os);
        newLaptop.setSerialNumber(serialNumber);
        newLaptop.setReleaseDate(releaseDate);
        newLaptop.setAvailable(true);

        laptops.add(newLaptop);
        LibraryItem.allItems.add(newLaptop);
        System.out.println("Laptop added successfully! New Laptop id:" + id);
    }

    /**
     * removes a laptop from the list of laptops
     * @param id id that is used to iterate through list
     */
    public void removeLaptop(int id) {

        Laptops laptopToRemove = null;
        for (Laptops laptop : laptops) {
            if (laptop.getId() == id) {
                laptopToRemove = laptop;
                break;
            }
        }
        if (laptopToRemove != null) {
            laptops.remove(laptopToRemove);
            LibraryItem.allItems.remove(laptopToRemove);
            System.out.println("Laptop removed successfully!");
        } else {
            System.out.println("Laptop not found!");
        }
    }

    /**
     * sets a value of a laptop in the list of laptops
     * @param id id that is used to iterate through list
     * @param field field that is being edited
     * @param newValue new value that will replace the old one
     */
    public void setLaptopValue(int id, String field, String newValue) {
        Laptops laptopToEdit = null;
        for (Laptops laptop : laptops) {
            if (laptop.getId() == id) {
                laptopToEdit = laptop;
                break;
            }
        }
        if (laptopToEdit == null) {
            System.out.println("Laptop not found!");
            return;
        }

        switch (field.toLowerCase()) {
            case "brand":
                laptopToEdit.setBrand(newValue);
                break;
            case "model":
                laptopToEdit.setModel(newValue);
                break;
            case "os":
            case "operating system":
                laptopToEdit.setOperatingSystem(newValue);
                break;
            case "serial":
            case "serial number":
                laptopToEdit.setSerialNumber(newValue);
                break;
            case "date":
            case "release date":
                try { laptopToEdit.setReleaseDate(LocalDate.parse(newValue)); } catch (Exception e) { System.out.println("Invalid date format (yyyy-MM-dd)"); return; }
                break;
            default:
                System.out.println("Invalid field. Available fields are: Brand, Model, OS, Serial, Date");
                return;
        }
        System.out.println("Laptop updated successfully!");
    }

    /**
     * loans a laptop to a customer
     * @param laptopID Id used to parse  list
     * @param customerID id of customer who is borrowing
     */
    public void loanLaptop(int laptopID, int customerID) {
        Laptops laptopToLoan = null;
        for (Laptops laptop : laptops) {
            if (laptop.getId() == laptopID) {
                laptopToLoan = laptop;
                break;
            }
        }
        if (laptopToLoan == null) {
            System.out.println("Laptop not found!");
            return;

        }
        if(!laptopToLoan.isAvailable()) {
            System.out.println("Laptop is already currently on loan.");
            return;
        }
        laptopToLoan.setAvailable(false);
        Loans newLoan = new Loans();
        newLoan.setLoanID(nextHighestNumber());
        newLoan.setCustomerID(customerID);
        newLoan.setItemID(laptopID);
        newLoan.setLoanDate(LocalDate.now());
        newLoan.setReturnDate(null);
        loans.add(newLoan);
    }

    /**
     * processes user returning a laptop
     * @param id ID used to parse lists
     */
    public void returnLaptop(int id) {

        Laptops laptopToReturn = null;
        for (Laptops laptop : laptops) {
            if (laptop.getId() == id) {
                laptopToReturn = laptop;
                break;
            }
        }
        if (laptopToReturn != null) {
            laptopToReturn.setAvailable(true);
            System.out.println("Laptop returned successfully!");
        } else {
            System.out.println("Laptop not found!");
        }
    }

    /**
     * adds a CD to the list of CDs
     * @param name
     * @param genre
     * @param audienceScore
     * @param releaseDate
     */
    public void addCD(String name, String genre, String artist, float audienceScore, LocalDate releaseDate) {
        int id = nextHighestNumber();
        String type = "CD";

        CDs newCD = new CDs();
        newCD.setId(id);
        newCD.setType(type);
        newCD.setName(name);
        newCD.setGenre(genre);
        newCD.setArtist(artist);
        newCD.setAudienceScore(audienceScore);
        newCD.setYear(releaseDate);
        newCD.setAvailable(true);

        cds.add(newCD);
        LibraryItem.allItems.add(newCD);
        System.out.println("CD added successfully! New CD id:" + id);
    }

    /**
     * removes a CD from the list of CDs
     * @param id id used to parse lists
     */
    public void removeCD(int id) {

        CDs cdToRemove = null;
        for (CDs cd : cds) {
            if (cd.getId() == id) {
                cdToRemove = cd;
                break;
            }
        }
        if (cdToRemove != null) {
            cds.remove(cdToRemove);
            LibraryItem.allItems.remove(cdToRemove);
            System.out.println("CD removed successfully!");
        } else {
            System.out.println("CD not found!");
        }
    }

    /**
     * sets a value of a CD in the list of CDs
     * @param id id that is used to iterate through list
     * @param field field that is being edited
     * @param newValue new value that will replace the old one
     */
    public void setCDValue(int id, String field, String newValue) {
        CDs cdToEdit = null;
        for (CDs cd : cds) {
            if (cd.getId() == id) {
                cdToEdit = cd;
                break;
            }
        }
        if (cdToEdit == null) {
            System.out.println("CD not found!");
            return;
        }

        switch (field.toLowerCase()) {
            case "name":
                cdToEdit.setName(newValue);
                break;
            case "genre":
                cdToEdit.setGenre(newValue);
                break;
            case "artist":
                cdToEdit.setArtist(newValue);
                break;
            case "audience score":
                try { cdToEdit.setAudienceScore(Short.parseShort(newValue)); } catch (NumberFormatException e) { System.out.println("Invalid audience score"); return; }
                break;
            case "date":
            case "release date":
                try { cdToEdit.setYear(LocalDate.parse(newValue)); } catch (Exception e) { System.out.println("Invalid date format (yyyy-MM-dd)"); return; }
                break;
            default:
                System.out.println("Invalid field. Available fields are: Name, Genre, Studio, Audience Score, Rotten Tomatoes, Date");
                return;
        }
        System.out.println("CD updated successfully!");
    }

    /**
     * loans a CD to a customer
     * @param cdID ID to iterate through lists
     * @param customerID customer who is borrowing item
     */
    public void loanCD(int cdID, int customerID) {
        CDs cdToLoan = null;

        for (CDs cd : cds) {
            if (cd.getId() == cdID) {
                cdToLoan = cd;
                break;
            }
        }
        if (cdToLoan == null) {
            System.out.println("CD not found!");
            return;
        }
        if (!cdToLoan.isAvailable()) {
            System.out.println("CD is already currently on loan.");
            return;
        }
        cdToLoan.setAvailable(false);
        Loans newLoan = new Loans();
        newLoan.setLoanID(nextHighestNumber());
        newLoan.setCustomerID(customerID);
        newLoan.setItemID(cdID);
        newLoan.setLoanDate(LocalDate.now());
        newLoan.setReturnDate(null);
        loans.add(newLoan);

    }

    /**
     * processes user returning a CD
     * @param id ID to iterate through list
     */
    public void returnCD(int id) {

        CDs cdToReturn = null;
        for (CDs cd : cds) {
            if (cd.getId() == id) {
                cdToReturn = cd;
                break;
            }
        }
        if (cdToReturn != null) {
            cdToReturn.setAvailable(true);
            System.out.println("CD returned successfully!");
        } else {
            System.out.println("CD not found!");
        }
    }

    /**
     * Lists all items in books, laptops and cds
     */
    public void listAllItems() {
        System.out.println("Books:");
        for (Books book : books) {
            System.out.println(book);
        }
        System.out.println("Laptops:");
        for (Laptops laptop : laptops) {
            System.out.println(laptop);
        }
        System.out.println("CDs:");
        for (CDs cd : cds) {
            System.out.println(cd);
        }
    }

    /**
     * Lists all loans
     */
    public void listAllLoans() {
        System.out.println("Loans:");
        for (Loans loan : loans) {
            System.out.println(loans);
        }
    }

    /**
     * Adds a customer to the list of customers
     * @param id
     * @param name
     * @param email
     * @param phoneNumber
     * @param address
     */
    public void addCustomer(int id, String name, String email, String phoneNumber, String address) {
        Customer newcustomer = new Customer();
        newcustomer.setCustomerID(id);
        newcustomer.setName(name);
        newcustomer.setEmail(email);
        newcustomer.setPhoneNumber(phoneNumber);
        newcustomer.setAddress(address);
        customers.add(newcustomer);
        System.out.println("Customer added successfully! New Customer with ID:" + id);
    }

    /**
     * Removes a customer from the list of customers
     * @param id ID to iterate through list
     */
    public void removeCustomer(int id) {

        Customer customerToRemove = null;
        for (Customer customer : customers) {
            if (customer.getCustomerID() == id) {
                customerToRemove = customer;
                break;
            }
        }
        if (customerToRemove != null) {
            customers.remove(customerToRemove);
            System.out.println("Customer removed successfully!");
        } else {
            System.out.println("Customer not found!");
        }
    }

    /**
     * edits a customer's data
     * @param id id that is used to iterate through list
     * @param field field that is being edited
     * @param newValue new value that will replace the old one
     */
    public void setUserData(int id, String field, String newValue) {
        Customer customerToEdit = null;
        for (Customer customer : customers) {
            if (customer.getCustomerID() == id) {
                customerToEdit = customer;
                break;
            }
        }
        if (customerToEdit == null) {
            System.out.println("Book not found!");
            return;
        }

        switch (field.toLowerCase()) {
            case "name":
                customerToEdit.setName(newValue);
                break;
            case "email":
                customerToEdit.setEmail(newValue);
                break;
            case "phone":
                customerToEdit.setPhoneNumber(newValue);
                break;
            case "phone number":
                customerToEdit.setPhoneNumber(newValue);
                break;
            case "address":
                customerToEdit.setAddress(newValue);
                break;
            default:
                System.out.println("Invalid field. Available fields are: name, email, phone, phone number, address");
                return;
        }
        System.out.println("Customer data updated successfully!");

    }

    /**
     * Sets customer fine to zero
     * @param id id for iterating lists
     */
    public void payFines(int id) {
        for (Customer customer : customers) {
            if (customer.getCustomerID() == id) {
                System.out.println("Fines for customer with ID: " + id + " are: " + customer.getFines());
                customer.setFines(0);
                System.out.println("Fines paid successfully!");
                break;
            }else {
                System.out.println("Customer not found!");
                return;
            }
        }
    }

    /**
     * Calculates all fines of customers and prints them to console
     */
    public void calculateFines() {
        LocalDate currentDate = LocalDate.now();
        double fineRate = 0.50; // 50 cents per day

        for (Loans loan : loans) {
            if (loan.getReturnDate() == null) {
                LocalDate dueDate = loan.getLoanDate().plusDays(14); // 2 week loan period

                if (currentDate.isAfter(dueDate)) {
                    long daysOverdue = currentDate.toEpochDay() - dueDate.toEpochDay();
                    double fine = daysOverdue * fineRate;
                    loan.setFineAmount(fine);

                    for (Customer customer : customers) {
                        if (customer.getCustomerID() == loan.getCustomerID()) {
                            customer.setFines(customer.getFines() + fine);
                            System.out.println("Fine for customer with ID: " + loan.getCustomerID() + " is: " + fine);
                            break;
                        }
                    }
                }
            }
        }

    }

    /**
     * Searches books list by the title of book
     * @param bookName Search term for finding book
     */
    public void searchBooks(String bookName) {
        for (Books book : books) {
            if (book.getTitle().toLowerCase().contains(bookName.toLowerCase())) {
                System.out.println(book);
            }
        }
    }

    /**
     * Searches laptops list by the model of laptop
     * @param laptopName Search term for finding laptop
     */
    public void searchLaptops(String laptopName) {
        for (Laptops laptop : laptops) {
            if (laptop.getModel().toLowerCase().contains(laptopName.toLowerCase())) {
                System.out.println(laptop);
            }
        }
    }

    /**
     * Searches cds list by the name of cd
     * @param cdName Search term for finding CD
     */
    public void searchCDs(String cdName) {
        for (CDs cd : cds) {
            if (cd.getName().toLowerCase().contains(cdName.toLowerCase())) {
                System.out.println(cd);
            }
        }
    }

    /**
     * Searches customers list by the name of customer
     * @param userName Search term for finding user by name
     */
    public void customerNameSearch(String userName) {
        for(Customer customer : customers) {
            if (customer.getName().toLowerCase().contains(userName.toLowerCase())) {
                System.out.println(customer);
            }else {
                System.out.println("User not found!");
                return;
            }
        }
    }

    /**
     * Searches customers list by the ID of customer
     * @param userID ID for iterating through list
     */
    public void cusomerIDSearch(int userID) {
        for(Customer customer : customers) {
            if (customer.getCustomerID() == userID) {
                System.out.println(customer);
            }else{
                System.out.println("User not found!");
                return;
            }
        }
    }

    /**
     * Gets save location from Menu and saves CSVs to saveLocation
     * @param saveLocation the location where the file wll be saved
     */
    public void makeUpdatedCSVs (String saveLocation) {
        // logic for saving csv here; assumes saveLocation is a directory path
        if (saveLocation == null || saveLocation.isEmpty()) {
            System.out.println("Error saving CSV file");
            System.out.println("Save location was not provided.");
            return;
        }
        try {
            saveListToCSV(books, saveLocation + File.separator + "books.csv");
            saveListToCSV(laptops, saveLocation + File.separator + "laptops.csv");
            saveListToCSV(cds, saveLocation + File.separator + "cds.csv");
            saveListToCSV(customers, saveLocation + File.separator + "customers.csv");
            saveListToCSV(loans, saveLocation + File.separator + "loans.csv");
            System.out.println("CSV file saved successfully!");
        } catch (Exception e) {
            System.out.println("Error saving CSV file");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reurns the next highest ID number in allitems to avoid conflicts
     * @return returns the next highest number in allItems
     */
    public int nextHighestNumber () {
        int max = 0;
            for (LibraryItem item : LibraryItem.allItems) {
                int id = item.getId();
                if (id > max) {
                    max = id;
                }
            }

            return (max + 1);
    }

    /**
     * Updates allItems
     */
    public void updateComboList () {
        ArrayList<LibraryItem> tempList = new ArrayList<>();

        tempList.addAll(books);
        tempList.addAll(laptops);
        tempList.addAll(cds);
        //if the temp list isnt the same as the actual list, update it
        if (LibraryItem.allItems.equals(tempList)) {
            return;
        } else {
            LibraryItem.allItems.clear();
            LibraryItem.allItems.addAll(tempList);
        }
    }

    public List<Books> getBooks() {
        return books;
    }

    public List<Laptops> getLaptops() {
        return laptops;
    }

    public List<CDs> getCDs() {
        return cds;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Loans> getLoans() {
        return loans;
    }

    /**
     * Saves a list of objects to a CSV file
     * @param list the list that is being written into the file
     * @param filePath The place where the file will be stored
     * @param <T>
     * @throws IOException IO Exeption
     * @throws CsvRequiredFieldEmptyException tried to add empty data into CSV
     * @throws CsvDataTypeMismatchException Type Mismatch
     */
    public <T> void saveListToCSV(List<T> list, String filePath) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try(Writer writer = new BufferedWriter(new FileWriter(filePath))){
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer).build();
            beanToCsv.write(list);
            writer.close();
        }
    }

    /**
     * Clears all books from the list
     * Was only used for testing purposes.
     */
    public void clearBooks() {
        books.clear();
    }
}
