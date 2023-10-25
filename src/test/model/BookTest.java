package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static model.Genre.*;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    private Book testBook;

    @BeforeEach
    public void runBefore() {
        testBook = new Book("Pride and Prejudice", "Jane Austen", classic);
    }

    @Test
    public void testConstructor() {
        assertEquals("Pride and Prejudice", testBook.getTitle());
        assertEquals("Jane Austen", testBook.getAuthor());
        assertEquals(classic, testBook.getGenre());
        assertEquals(0, testBook.getRating());
        assertEquals("no review yet.", testBook.getReview());
    }

    @Test
    public void testRateBook() {
        assertFalse(testBook.rateBook(32));
        assertFalse(testBook.rateBook(6));
        assertFalse(testBook.rateBook(0));

        assertTrue(testBook.rateBook(5));
        assertEquals(5,testBook.getRating());

        assertTrue(testBook.rateBook(1));
        assertEquals(1,testBook.getRating());
    }

    @Test
    public void testReviewBook() {
        testBook.reviewBook("Great book! I love Mr. Darcy.");
        assertEquals("Great book! I love Mr. Darcy.",testBook.getReview());
    }

    @Test
    public void testCheckSameBookTrue() {
        Book testBook2 = new Book("Pride and Prejudice", "Jane Austen", classic);
        assertTrue(testBook.checkSameBook(testBook2));
    }

    @Test
    public void testCheckSameBookFalse() {
        Book testBook2 = new Book("Pride and Prejudice", "Jane Austen", classic);
        testBook2.reviewBook("awesome.");
        assertFalse(testBook.checkSameBook(testBook2));

        Book testBook3 = new Book("Pride, Prejudice, and Zombies", "Jane Austen", classic);
        assertFalse(testBook.checkSameBook(testBook3));

        Book testBook4 = new Book("Pride and Prejudice", "Jane Doe", classic);
        assertFalse(testBook.checkSameBook(testBook4));

        Book testBook5 = new Book("Pride and Prejudice", "Jane Austen", fantasy);
        assertFalse(testBook.checkSameBook(testBook5));

        Book testBook6 = new Book("Pride and Prejudice", "Jane Austen", classic);
        testBook6.rateBook(5);
        assertFalse(testBook.checkSameBook(testBook6));
    }

    @Test
    public void checkContainsBookTrue() {
        Book testBook2 = new Book("Pride and Prejudice", "Jane Austen", classic);
        List<Book> bookList = new ArrayList<>();
        bookList.add(testBook2);
        assertTrue(testBook.containsBook(bookList));
    }
    @Test
    public void checkContainsBookFalse() {
        Book testBook3 = new Book("Pride and Prejudice", "Jane Doe", classic);
        List<Book> bookList = new ArrayList<>();
        bookList.add(testBook3);
        assertFalse(testBook.containsBook(bookList));
    }
    @Test
    public void checkContainsBookEmpty() {
        List<Book> bookList = new ArrayList<>();
        assertFalse(testBook.containsBook(bookList));
    }

}