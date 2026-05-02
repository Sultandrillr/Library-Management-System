package src.main.java;

import java.io.FileNotFoundException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        LibraryController controller = new LibraryController();
        Menu menu = new Menu();
        menu.mainMenu();
    }
}