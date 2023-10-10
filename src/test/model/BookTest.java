package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static model.Genre.*;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    Book testBook;

    @BeforeEach
    public void runBefore() {
        testBook = new Book("Pride and Prejudice", "Jane Austen", CLASSIC);
    }

    @Test
    public void testConstructor() {
        assertEquals("Pride and Prejudice", testBook.getTitle());
        assertEquals("Jane Austen", testBook.getAuthor());
        assertEquals(CLASSIC, testBook.getGenre());
        assertEquals(0, testBook.getRating());
        assertNull(testBook.getReview());
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
}