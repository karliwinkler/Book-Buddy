package ui;

import model.Book;
import model.BookCollection;
import model.Genre;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static model.Genre.*;

// BookBuddy reading tracker application
public class BookBuddyApp {
    private static final String JSON_STORE = "./data/bookbuddy.json";
    private BookCollection bookCollection;
    private Scanner input;
    private Boolean mainDisplayed;
    private Boolean menu1Displayed;
    private Boolean menu2Displayed;
    private Boolean menu3Displayed;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // effects: runs book buddy application
    public BookBuddyApp() {
        runBookBuddy();
    }

    // modifies: this
    // effects: processes input from user
    private void runBookBuddy() {
        boolean keepGoing = true;
        String command = null;

        initialize();
        displayMainMenu();

        while (keepGoing) {
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                commandControl(command);
            }
        }
        System.out.println("Thanks for using BookBuddy! Happy reading!");
    }

    // effects: initializes book collection
    private void initialize() {
        bookCollection = new BookCollection();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // effects: displays main menu of options
    private void displayMainMenu() {
        System.out.println("\nWelcome to Book Buddy! Select an option below:");
        System.out.println("\t1 -> view books you've read");
        System.out.println("\t2 -> view books you want to read");
        System.out.println("\t3 -> view favourite books");
        System.out.println("\ts -> save your books");
        System.out.println("\tl -> load saved books");
        System.out.println("Press \"q\" at any time to quit, or \"b\" to return to main menu.");
        mainDisplayed = true;
        menu1Displayed = false;
        menu2Displayed = false;
        menu3Displayed = false;
    }

    // effects: displays reading list and options
    private void displayMenu1() {
        printBookList(bookCollection.getReadBooks());
        System.out.println("\nIf you'd like to add a new book, press \"a\" ");
        System.out.println("To rate a book, press \"r\" ");
        System.out.println("To review a book, press \"v\" ");
        System.out.println("To see your reading stats, press \"s\" ");
        mainDisplayed = false;
        menu1Displayed = true;
        menu2Displayed = false;
        menu3Displayed = false;
    }

    // effects: displays want to read list and options
    private void displayMenu2() {
        printBookList(bookCollection.getWantToRead());
        System.out.println("\nTo add a book to your want to read, press \"a\" ");
        mainDisplayed = false;
        menu1Displayed = false;
        menu2Displayed = true;
        menu3Displayed = false;
    }

    // effects: displays favourites list and options
    private void displayMenu3() {
        printBookList(bookCollection.getFavouriteBooks());
        System.out.println("\nTo add a book to your favourites, press \"a\"");
        mainDisplayed = false;
        menu1Displayed = false;
        menu2Displayed = false;
        menu3Displayed = true;
    }

    // effects: directs command to appropriate function depending on which menu is displayed
    private void commandControl(String command) {
        if (mainDisplayed) {
            processCommandMM(command);
        }
        if (menu1Displayed) {
            processCommandM1(command);
        }
        if (menu2Displayed) {
            processCommandM2(command);
        }
        if (menu3Displayed) {
            processCommandM3(command);
        }
    }

    // modifies: this
    // effects: processes command based on main menu
    private void processCommandMM(String command) {
        if (command.equals("1")) {
            displayMenu1();
        } else if (command.equals("2")) {
            displayMenu2();
        } else if (command.equals("3")) {
            displayMenu3();
        } else if (command.equals("s")) {
            saveBookCollection();
        } else if (command.equals("l")) {
            loadBookCollection();
        }
    }

    // modifies: this
    // effects: processes command based on menu 1 (read list menu)
    private void processCommandM1(String command) {
        if (command.equals("a")) {
            addBookToRead();
        } else if (command.equals("r")) {
            rateBook();
        } else if (command.equals("v")) {
            reviewBook();
        } else if (command.equals("s")) {
            showStats();
        } else if (command.equals("b")) {
            displayMainMenu();
        }
    }

    // modifies: this
    // effects: processes command based on menu 2 (want to read list menu)
    private void processCommandM2(String command) {
        if (command.equals("a")) {
            addBookToWantToRead();
        } else if (command.equals("b")) {
            displayMainMenu();
        }
    }

    // modifies: this
    // effects: processes command based on menu 3 (favourites menu)
    private void processCommandM3(String command) {
        if (command.equals("a")) {
            addBookToFavs();
        } else if (command.equals("b")) {
            displayMainMenu();
        }
    }


    //effects: prints list of books from given list
    private void printBookList(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("You currently have no books in this list.");
        }

        for (Book b : books) {
            System.out.println(b.getTitle() + " by " + b.getAuthor() + " - " + b.getRating()
                    + "/5 stars," + " review: " + b.getReview());
        }
    }


    // modifies: this
    // effects: adds book to list of books read
    private void addBookToRead() {
        System.out.println("\nPlease enter the book's title:");
        String title = input.next();
        System.out.println("\nPlease enter the book's author:");
        String author = input.next();
        System.out.println("Choose a genre that best describes the book:");
        System.out.println("\tc -> classic");
        System.out.println("\tf -> fantasy");
        System.out.println("\tm -> mystery");
        System.out.println("\tr -> romance");
        System.out.println("\tn -> non-fiction");
        System.out.println("\ts -> sci-fi");
        System.out.println("\to -> contemporary");
        Genre genre = selectGenre(input.next());
        String genreStr = genre.toString();

        if (genreStr.equals("none")) {
            System.out.println("Invalid genre, please try again.");
            addBookToRead();
        } else {
            Book book = new Book(title, author, genre);
            bookCollection.readBook(book);
            System.out.printf("Successfully added " + title + " by " + author + ", genre: " + genre
                    + " to the books you've read!");
        }
    }

    // effects: returns corresponding genre based on user input, if input is invalid, returns genre none
    private Genre selectGenre(String scan) {
        if (scan.equals("c")) {
            return classic;
        } else if (scan.equals("f")) {
            return fantasy;
        } else if (scan.equals("m")) {
            return mystery;
        } else if (scan.equals("r")) {
            return romance;
        } else if (scan.equals("n")) {
            return nonfiction;
        } else if (scan.equals("s")) {
            return scifi;
        } else if (scan.equals("o")) {
            return contemporary;
        }
        return none;
    }

    // modifies: this
    // effects: adds rating out of five stars to book
    private void rateBook() {
        System.out.println("\nPlease enter the title of the book you'd like to rate:");
        Book book = bookCollection.getBook(input.next());
        if (book.getTitle().equals("")) {
            System.out.println("Whoops! It looks like you haven't read that book yet. Please try again.");
            displayMenu1();
        } else {
            System.out.println("\nPlease enter your rating out of 5 stars.");
            int rate = Integer.parseInt(input.next());
            if (book.rateBook(rate)) {
                System.out.println("Successfully rated " + book.getTitle() + " " + rate + " stars!");
            } else {
                System.out.println("\nPlease choose a number between 1 and 5");
                rateBook();
            }
        }
    }

    // modifies: this
    // effects: adds user's review to book
    private void reviewBook() {
        System.out.println("\nPlease enter the title of the book you'd like to review:");
        Book book = bookCollection.getBook(input.next());
        if (book.getTitle().equals("")) {
            System.out.println("Whoops! It looks like you haven't read that book yet. Please try again.");
            displayMenu1();
        } else {
            System.out.println("\nPlease enter your review of " + book.getTitle() + ":");
            book.reviewBook(input.next());
            System.out.println("Successfully reviewed " + book.getTitle() + "!");
        }
    }

    // effects: displays user's number of books read, top genre and top author
    private void showStats() {
        int num = bookCollection.getReadBooks().size();

        if (num == 0) {
            System.out.println("You haven't logged any books yet! Add a book to view your stats.");
        } else {
            String topGenre = bookCollection.getTopGenre().toString();
            String topAuthor = bookCollection.getTopAuthor();
            System.out.println("You have read a total of " + num + " books!");
            System.out.println("Your top genre is: " + topGenre + ".");
            System.out.println("Your top author is: " + topAuthor + ".");
        }
    }

    // modifies: this
    // effects: adds book to want to read list
    private void addBookToWantToRead() {
        System.out.println("\nPlease enter the book's title:");
        String title = input.next();
        System.out.println("\nPlease enter the book's author:");
        String author = input.next();
        System.out.println("Choose a genre that best describes the book:");
        System.out.println("\tc -> classic");
        System.out.println("\tf -> fantasy");
        System.out.println("\tm -> mystery");
        System.out.println("\tr -> romance");
        System.out.println("\tn -> non-fiction");
        System.out.println("\ts -> sci-fi");
        System.out.println("\to -> contemporary");
        Genre genre = selectGenre(input.next());
        String genreStr = genre.toString();

        if (genreStr.equals("none")) {
            System.out.println("Invalid genre, please try again.");
            addBookToWantToRead();
        } else {
            Book book = new Book(title, author, genre);
            bookCollection.wantToReadBook(book);
            System.out.printf("Successfully added " + title + " by " + author + ", genre: " + genre
                    + " to books you want to read!");
        }
    }


    // modifies: this
    // effects: adds book to favourites list, if book is in read list, prints error message otherwise
    private void addBookToFavs() {
        System.out.println("\nPlease enter the title of a book you've read:");
        Book book = bookCollection.getBook(input.next());
        if (bookCollection.addFavouriteBook(book)) {
            System.out.printf("Successfully added " + book.getTitle() + " by " + book.getAuthor()
                    + ", genre: " + book.getGenre().toString() + " to your favourite books!");
        } else {
            System.out.println("Whoops! It looks like you haven't read that yet! Please try again.");
            displayMenu3();
        }
    }

    // effects: saves book collection to file
    private void saveBookCollection() {
        try {
            jsonWriter.open();
            jsonWriter.write(bookCollection);
            jsonWriter.close();
            System.out.println("Saved your books to " + JSON_STORE + "!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // modifies: this
    // effects: loads book collection from file
    private void loadBookCollection() {
        try {
            bookCollection = jsonReader.read();
            System.out.println("Loaded books from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}
