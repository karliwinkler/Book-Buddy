package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static model.Genre.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookCollectionTest {
    BookCollection testBC;
    Book testBookA;
    Book testBookB;
    Book testBookC;
    Book testBookD;

    @BeforeEach
    public void runBefore() {
        testBC = new BookCollection();
        testBookA = new Book("Pride and Prejudice", "Jane Austen", CLASSIC);
        testBookB = new Book("Emma", "Jane Austen", CLASSIC);
        testBookC = new Book("The Secret History", "Donna Tartt", MYSTERY);
        testBookD = new Book("The Handmaid's Tale", "Margaret Atwood", SCI_FI);
    }

    @Test
    public void testConstructor() {
        List<Book> books = testBC.getReadBooks();
        List<Book> favBooks = testBC.getFavouriteBooks();
        List<Book> wantToRead = testBC.getWantToRead();
        assertEquals(0, books.size());
        assertEquals(0, favBooks.size());
        assertEquals(0, wantToRead.size());
    }

    @Test
    public void testReadBook() {
        List<Book> books = testBC.getReadBooks();
        List<Book> booksWTR = testBC.getWantToRead();

        testBC.readBook(testBookA);
        assertEquals(1, books.size());
        assertEquals(testBookA, books.get(0));

        testBC.wantToReadBook(testBookB);
        testBC.wantToReadBook(testBookC);

        testBC.readBook(testBookB);
        assertEquals(2, books.size());
        assertEquals(testBookA, books.get(0));
        assertEquals(testBookB, books.get(1));
        assertEquals(1, booksWTR.size());
        assertEquals(testBookC, booksWTR.get(0));
    }

    @Test
    public void testAddFavoriteBookSuccess() {
        List<Book> favBooks = testBC.getFavouriteBooks();

        testBC.readBook(testBookA);
        testBC.readBook(testBookB);
        assertTrue(testBC.addFavouriteBook(testBookA));
        assertEquals(1, favBooks.size());
        assertEquals(testBookA, favBooks.get(0));

        assertTrue(testBC.addFavouriteBook(testBookB));
        assertEquals(2, favBooks.size());
        assertEquals(testBookA, favBooks.get(0));
        assertEquals(testBookB, favBooks.get(1));

    }

    @Test
    public void testAddFavoriteBookFail() {
        List<Book> favBooks = testBC.getFavouriteBooks();

        assertFalse(testBC.addFavouriteBook(testBookB));
        testBC.readBook(testBookA);
        assertFalse(testBC.addFavouriteBook(testBookC));

        assertEquals(0, favBooks.size());
    }

    @Test
    public void testWantToReadBookSuccess() {
        List<Book> WTR = testBC.getWantToRead();

        assertTrue(testBC.wantToReadBook(testBookA));
        assertEquals(1, WTR.size());
        assertEquals(testBookA, WTR.get(0));

        assertTrue(testBC.wantToReadBook(testBookB));
        assertEquals(2, WTR.size());
        assertEquals(testBookA, WTR.get(0));
        assertEquals(testBookB, WTR.get(1));
    }

    @Test
    public void testWantToReadBookFail() {
        List<Book> WTR = testBC.getWantToRead();

        assertTrue(testBC.wantToReadBook(testBookA));
        assertFalse(testBC.wantToReadBook(testBookA));
        testBC.readBook(testBookB);
        assertFalse(testBC.wantToReadBook(testBookB));
        assertEquals(1, WTR.size());
        assertEquals(testBookA, WTR.get(0));
    }

    @Test
    public void testRemoveFromWantToReadSuccess() {
        List<Book> WTR = testBC.getWantToRead();

        assertTrue(testBC.wantToReadBook(testBookA));
        assertTrue(testBC.wantToReadBook(testBookB));

        assertTrue(testBC.removeFromWantToRead(testBookA));
        assertEquals(1, WTR.size());
        assertEquals(testBookB, WTR.get(0));

        assertTrue(testBC.removeFromWantToRead(testBookB));
        assertEquals(0, WTR.size());
    }

    @Test
    public void testRemoveFromWantToReadFail() {
        List<Book> WTR = testBC.getWantToRead();

        assertFalse(testBC.removeFromWantToRead(testBookA));
        assertEquals(0, WTR.size());
        assertTrue(testBC.wantToReadBook(testBookA));
        assertFalse(testBC.removeFromWantToRead(testBookB));

        assertEquals(1, WTR.size());
        assertEquals(testBookA, WTR.get(0));
    }

    @Test
    public void testGetNumOfGenreEmpty() {
        assertEquals(0, testBC.getNumOfGenreRead(CLASSIC));
        assertEquals(0, testBC.getNumOfGenreRead(MYSTERY));
    }

    @Test
    public void testGetNumOfGenreRead() {
        testBC.readBook(testBookA);
        testBC.readBook(testBookB);
        testBC.readBook(testBookC);
        assertEquals(2, testBC.getNumOfGenreRead(CLASSIC));
        assertEquals(1, testBC.getNumOfGenreRead(MYSTERY));
        assertEquals(0, testBC.getNumOfGenreRead(ROMANCE));
    }

    @Test
    public void testGetTopGenre() {
        testBC.readBook(testBookC);
        assertEquals(MYSTERY, testBC.getTopGenre());

        testBC.readBook(testBookA);
        testBC.readBook(testBookB);
        testBC.readBook(testBookD);
        assertEquals(CLASSIC, testBC.getTopGenre());
    }

    @Test
    public void testGetTopGenreEqual() {
        testBC.readBook(testBookC);
        testBC.readBook(testBookD);
        assertEquals(MYSTERY, testBC.getTopGenre());
    }

    @Test
    public void testGetNumOfAuthorReadEmpty() {
        assertEquals(0, testBC.getNumOfAuthorRead("Jane Austen"));
        assertEquals(0, testBC.getNumOfAuthorRead("Donna Tartt"));
    }

    @Test
    public void testGetNumOfAuthorRead() {
        testBC.readBook(testBookA);
        testBC.readBook(testBookB);
        testBC.readBook(testBookC);
        assertEquals(2, testBC.getNumOfAuthorRead("Jane Austen"));
        assertEquals(1, testBC.getNumOfAuthorRead("Donna Tartt"));
        assertEquals(0, testBC.getNumOfAuthorRead("Margaret Atwood"));
    }

    @Test
    public void testGetTopAuthor() {
        testBC.readBook(testBookC);
        assertEquals("Donna Tartt", testBC.getTopAuthor());

        testBC.readBook(testBookA);
        testBC.readBook(testBookB);
        testBC.readBook(testBookD);
        assertEquals("Jane Austen", testBC.getTopAuthor());
    }

    @Test
    public void testGetTopAuthorEqual() {
        testBC.readBook(testBookC);
        testBC.readBook(testBookD);
        assertEquals("Donna Tartt", testBC.getTopAuthor());
    }
}