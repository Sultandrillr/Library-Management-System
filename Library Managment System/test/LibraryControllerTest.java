import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import src.main.java.Books;
import src.main.java.Customer;
import src.main.java.LibraryController;

import java.awt.print.Book;
import java.time.LocalDate;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;




//// Source - https://stackoverflow.com/a
//// Posted by Vahid, modified by community. See post 'Timeline' for change history
//// Retrieved 2025-12-14, License - CC BY-SA 4.0
//
//Assertions.assertEquals(<Expected>,<actual>); for checking things like int or string
//Assertions.assertTrue(<actual>); for checking boolean

public class LibraryControllerTest {
    LibraryController controllerTest = new LibraryController();
    Books bookTest = new Books();

    LocalDate releaseDate = LocalDate.of(2020, 1, 1);
    int id = 1;
    String type = "Book";
    String name = "The Witcher";
    String author = "R.R.Tolkien";
    String publisher = "MadHouse";
    String ISBN = "123456789";
    String series =  "The Witcher";
    double rating = 4.9;
    String description = "The Witcher";
    String language = "English";
    String genre = "Action";

    Customer customerTest = new Customer();
    String email = "KingVon@Hell.net";
    String phoneNumber = "0712345678";
    String address = "123 Fake Street";

    void setup(){
        bookTest.setId(id);
        bookTest.setType(type);
        bookTest.setTitle(name);
        bookTest.setAuthor(author);
        bookTest.setPublisher(publisher);
        bookTest.setISBN(ISBN);
        bookTest.setSeries(series);
        bookTest.setRating(rating);
        bookTest.setDescription(description);
        bookTest.setLanguage(language);
        bookTest.setGenre(genre);
        bookTest.setReleaseDate(releaseDate);
        bookTest.setAvailable(true);

        customerTest.setEmail(email);
        customerTest.setPhoneNumber(phoneNumber);
        customerTest.setAddress(address);

    }
    
    @Test
    public void testAddBook() {
        controllerTest.clearBooks();
        setup();
        controllerTest.addBook(name, author, publisher, ISBN, series, rating, description, language, genre, releaseDate);
        assertEquals(bookTest, (controllerTest.getBooks().get(0)));
        //Test fails for an unknown reason. Values are the same.
    }

    @Test
    public void removeBookTest() {
        controllerTest.addBook(name, author, publisher, ISBN, series, rating, description, language, genre, releaseDate);
        controllerTest.addBook(name, author, publisher, ISBN, series, rating, description, language, genre, releaseDate);


        controllerTest.removeBook(2);
        assertEquals(1, (controllerTest.getBooks()).size());
    }

    @Test
    public void loanBookTest() {
        controllerTest.addBook(name, author, publisher, ISBN, series, rating, description, language, genre, releaseDate);
        controllerTest.loanBook(1, 1);
        assertEquals(false, controllerTest.getBooks().get(0).isAvailable());

    }

    @Test
    public void returnBookTest() {
        controllerTest.addBook(name, author, publisher, ISBN, series, rating, description, language, genre, releaseDate);
        controllerTest.loanBook(1, 1);
        controllerTest.returnBook(1);
        assertEquals(true, controllerTest.getBooks().get(0).isAvailable());
    }

    @Test
    public void listAllItemsTest(){
        controllerTest.addBook(name, author, publisher, ISBN, series, rating, description, language, genre, releaseDate);
        controllerTest.listAllItems();
    }

    @Test
    public void calculateFinesTest(){
        controllerTest.addCustomer(id, name, email, phoneNumber, address);
        controllerTest.addBook(name, author, publisher, ISBN, series, rating, description, language, genre, releaseDate);
        controllerTest.loanBook(1, 1);
        controllerTest.calculateFines();
        assertEquals(184.5, controllerTest.getCustomers().get(0).getFines());
    }

    @Test
    public void payFinesTest(){
        controllerTest.addCustomer(id, name, email, phoneNumber, address);
        controllerTest.addBook(name, author, publisher, ISBN, series, rating, description, language, genre, releaseDate);
        controllerTest.loanBook(1, 1);
        controllerTest.calculateFines();
        controllerTest.payFines(1);
        assertEquals(0, controllerTest.getCustomers().get(0).getFines());
    }

    @Test
    public void searchBooksTest(){
        controllerTest.addBook(name, author, publisher, ISBN, series, rating, description, language, genre, releaseDate);
        controllerTest.searchBooks("The");
    }

    @Test
    public void customerIDSearchTest(){
        controllerTest.addCustomer(id, name, email, phoneNumber, address);
        controllerTest.cusomerIDSearch(1);
    }

    @Test
    public void makeUpdatedCSVsTest(){
        controllerTest.addBook(name, author, publisher, ISBN, series, rating, description, language, genre, releaseDate);
        try {
            Path tempDir = Files.createTempDirectory("libcsvtest");
            tempDir.toFile().deleteOnExit();
            controllerTest.makeUpdatedCSVs(tempDir.toString());
        } catch (Exception e) {
            fail("Exception during CSV save test: " + e.getMessage());
        }
    }

}
