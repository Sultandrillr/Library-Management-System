package src.main.java;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
    private final LibraryController controller = new LibraryController();
    private final Scanner scanner = new Scanner(System.in);

    //basically the interface. This is what the user will interact with. Rest of code will run outside of this. All of the logic for what function to call based on user input
    public Menu() {

    }

    /**
     * handles the main menu and calls the appropriate functions based on user input
     * @throws FileNotFoundException
     */
    public void mainMenu () throws FileNotFoundException {
        boolean active = true;
        controller.loadData();

        while(active){
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Items");
            System.out.println("2. Customers");
            System.out.println("3. Loans");
            System.out.println("4. Save CSVs");
            System.out.println("5. Save CSV's & Exit");
            System.out.println("6. Exit Without Saving");
            System.out.println("");
            System.out.print("Choice: ");

            switch (scanner.nextLine()) {
                case "1":
                    itemsMenu();
                    break;

                case "2":
                    customersMenu();
                    break;

                case "3":
                    loansMenu();
                    break;

                case "4":
                    // Move user interaction here: choose directory and pass to controller
                    System.out.println("Window might pop up in an unexpected location.");
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Select CSV save location");
                    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int userSelection = fileChooser.showSaveDialog(null);
                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        String saveLocation = fileChooser.getSelectedFile().getAbsolutePath();
                        controller.makeUpdatedCSVs(saveLocation);
                    } else {
                        System.out.println("Save cancelled.");
                    }
                    break;

                case "5":
                    JFileChooser fileChooser2 = new JFileChooser();
                    fileChooser2.setDialogTitle("Select CSV save location");
                    fileChooser2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int userSelection2 = fileChooser2.showSaveDialog(null);
                    if (userSelection2 == JFileChooser.APPROVE_OPTION) {
                        String saveLocation2 = fileChooser2.getSelectedFile().getAbsolutePath();
                        controller.makeUpdatedCSVs(saveLocation2);
                    } else {
                        System.out.println("Save cancelled.");
                    }
                    active = false;

                case "6":
                    active = false;

                default:
                    System.out.println("Invalid input");
            }
        }
    }
    private void itemsMenu() {
        boolean back = false;
        while(!back){
            System.out.println("\n=== ITEMS MENU ===");
            System.out.println("1. Books");
            System.out.println("2. Laptops");
            System.out.println("3. CDs");
            System.out.println("4. Search Items");
            System.out.println("5. List All Items");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            switch (scanner.nextLine()) {
                case "1":
                    booksMenu();
                    break;
                case "2":
                    laptopsMenu();
                    break;
                case "3":
                    cdsMenu();
                    break;
                case "4":
                    searchItemMenu();
                    break;
                case "5":
                    controller.listAllItems();
                case "0":
                    back = true;

                default: System.out.println("Invalid input");
            }
        }
    }
    private void booksMenu(){
        boolean back = false;
        while(!back){
            System.out.println("\n=== BOOKS MENU ===");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Loan Book");
            System.out.println("4. Return Book");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("Enter book details:");
                    System.out.println("Enter book Title:");
                    String name = scanner.nextLine();
                    System.out.println("Enter book Author:");
                    String author = scanner.nextLine();
                    System.out.println("Enter book Publisher:");
                    String publisher = scanner.nextLine();
                    System.out.println("Enter book ISBN:");
                    String ISBN = scanner.nextLine();
                    System.out.println("Enter book Series:");
                    String series = scanner.nextLine();
                    System.out.println("Enter book Rating:");
                    double rating = scanner.nextDouble();
                    scanner.nextLine(); // consume newline after double
                    System.out.println("Enter book Descripion");
                    String description = scanner.nextLine();
                    System.out.println("Enter book Language:");
                    String language = scanner.nextLine();
                    System.out.println("Enter book Genre:");
                    String genre = scanner.nextLine();
                    System.out.println("Enter book Release Date (yyyy-MM-dd):");
                    LocalDate releaseDate = LocalDate.parse(scanner.nextLine());
                    controller.addBook(name, author, publisher, ISBN, series, rating, description, language, genre, releaseDate);
                    break;
                case "2":
                    System.out.print("Enter Book ID to remove: ");
                    int removeBookId = scanner.nextInt();
                    scanner.nextLine();
                    controller.removeBook(removeBookId);
                    break;
                case "3":
                    System.out.print("Enter Book ID to loan: ");
                    int loanBookId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Emter customer ID to loan book:");
                    int loanCustomerId = scanner.nextInt();
                    scanner.nextLine();
                    controller.loanBook(loanBookId, loanCustomerId);
                    break;
                case "4":
                    System.out.print("Enter Book ID to return: ");
                    int returnBookId = scanner.nextInt();
                    scanner.nextLine();
                    controller.returnBook(returnBookId);
                    break;
                case "0":
                    back = true;
                default:
                    System.out.println("Invalid input");
            }
        }
    }
    private void laptopsMenu(){
        boolean back = false;
        while(!back){
            System.out.println("\n=== LAPTOPS MENU ===");
            System.out.println("1. Add Laptop");
            System.out.println("2. Remove Laptop");
            System.out.println("3. Loan Laptop");
            System.out.println("4. Return Laptop");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("Enter Laptop details:");
                    System.out.print("Brand: ");
                    String brand = scanner.nextLine();
                    System.out.print("Model: ");
                    String model = scanner.nextLine();
                    System.out.print("Operating System: ");
                    String os = scanner.nextLine();
                    System.out.print("Serial Number: ");
                    String serial = scanner.nextLine();
                    System.out.print("Release Date (yyyy-MM-dd): ");
                    LocalDate lRelease = LocalDate.parse(scanner.nextLine());
                    controller.addLaptop(brand, model, os, serial, lRelease);
                    break;
                case "2":
                    System.out.print("Enter Laptop ID to remove: ");
                    int removeLaptopId = scanner.nextInt();
                    scanner.nextLine();
                    controller.removeLaptop(removeLaptopId);
                    break;
                case "3":
                    System.out.print("Enter Laptop ID to loan: ");
                    int loanLaptopId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter customer ID to loan laptop: ");
                    int loanCustomerId = scanner.nextInt();
                    scanner.nextLine();
                    controller.loanLaptop(loanLaptopId, loanCustomerId);
                    break;
                case "4":
                    System.out.print("Enter Laptop ID to return: ");
                    int returnLaptopId = scanner.nextInt();
                    scanner.nextLine();
                    controller.returnLaptop(returnLaptopId);
                    break;
                case "0":
                    back = true;
                default: System.out.println("Invalid input");
            }
        }
    }
    private void cdsMenu(){
        boolean back = false;
        while(!back){
            System.out.println("\n=== CDS MENU ===");
            System.out.println("1. Add CD");
            System.out.println("2. Remove CD");
            System.out.println("3. Loan CD");
            System.out.println("4. Return CD");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("Enter CD details:");
                    System.out.print("Name: ");
                    String cdName = scanner.nextLine();
                    System.out.print("Genre: ");
                    String cdGenre = scanner.nextLine();
                    System.out.print("Studio: ");
                    String studio = scanner.nextLine();
                    System.out.print("Audience Score (number): ");
                    short audience = scanner.nextShort();
                    scanner.nextLine();
                    System.out.print("Release Date (yyyy-MM-dd): ");
                    LocalDate cdRelease = LocalDate.parse(scanner.nextLine());
                    controller.addCD(cdName, cdGenre, studio, audience, cdRelease);
                    break;
                case "2":
                    System.out.print("Enter CD ID to remove: ");
                    int removeCdId = scanner.nextInt();
                    scanner.nextLine();
                    controller.removeCD(removeCdId);
                    break;
                case "3":
                    System.out.print("Enter CD ID to loan: ");
                    int loanCdId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter customer ID to loan CD: ");
                    int loanCustomerId = scanner.nextInt();
                    scanner.nextLine();
                    controller.loanCD(loanCdId, loanCustomerId);
                    break;
                case "4":
                    System.out.print("Enter CD ID to return: ");
                    int returnCdId = scanner.nextInt();
                    scanner.nextLine();
                    controller.returnCD(returnCdId);
                    break;
                case "0":
                    back = true;
                default: System.out.println("Invalid input");
            }
        }
    }
    private void searchItemMenu(){
        boolean back = false;
        while(!back){
            System.out.println("\n=== SEARCH ITEMS ===");
            System.out.println("1. Search Books");
            System.out.println("2. Search Laptops");
            System.out.println("3. Search CDs");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            switch (scanner.nextLine()) {
                case "1":
                    System.out.print("Enter book name to search: ");
                    String searchBook = scanner.nextLine();
                    controller.searchBooks(searchBook);
                    break;
                case "2":
                    System.out.print("Enter laptop model to search: ");
                    String searchLaptop = scanner.nextLine();
                    controller.searchLaptops(searchLaptop);
                    break;
                case "3":
                    System.out.print("Enter CD name to search: ");
                    String searchCd = scanner.nextLine();
                    controller.searchCDs(searchCd);
                    break;
                case "0":
                    back = true;
                default: System.out.println("Invalid input");
            }
        }
    }

    private void customersMenu(){
        boolean back = false;
        while (!back){
            System.out.println("\n=== CUSTOMERS MENU ===");
            System.out.println("1. Add Customer");
            System.out.println("2. Remove Customer");
            System.out.println("3. Search Customer (Name)");
            System.out.println("4. Search Customer (ID)");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            switch (scanner.nextLine()){
                case "1":
                    System.out.println("Enter customer details:");
                    System.out.print("ID: ");
                    int custId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Name: ");
                    String custName = scanner.nextLine();
                    System.out.print("Email: ");
                    String custEmail = scanner.nextLine();
                    System.out.print("Phone Number: ");
                    String custPhone = scanner.nextLine();
                    System.out.print("Address: ");
                    String custAddress = scanner.nextLine();
                    controller.addCustomer(custId, custName, custEmail, custPhone, custAddress);
                    break;
                case "2":
                    System.out.print("Enter customer ID to remove: ");
                    int removeCustomerId = scanner.nextInt();
                    scanner.nextLine();
                    controller.removeCustomer(removeCustomerId);
                    break;
                case "3":
                    System.out.print("Enter customer name to search: ");
                    String nameSearch = scanner.nextLine();
                    controller.customerNameSearch(nameSearch);
                    break;
                case"4":
                    System.out.print("Enter customer ID to search: ");
                    int idSearch = scanner.nextInt();
                    scanner.nextLine();
                    controller.cusomerIDSearch(idSearch);
                    break;
                case "0":
                    back = true;
                default: System.out.println("Invalid input");
            }
        }
    }

    private void loansMenu(){
        boolean back = false;
        while (!back){
            System.out.println("\n=== LOANS MENU ===");
            System.out.println("1. List All Loans");
            System.out.println("2. Calculate Fines");
            System.out.println("3. Pay Fines");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            switch (scanner.nextLine()){
                case "1":
                    controller.listAllLoans();
                    break;
                case "2":
                    controller.calculateFines();
                    break;
                case "3":
                    System.out.print("Enter customer ID to pay fines: ");
                    int payId = scanner.nextInt();
                    scanner.nextLine();
                    controller.payFines(payId);
                    break;
                case "0":
                    back = true;
            }
        }
    }
}
