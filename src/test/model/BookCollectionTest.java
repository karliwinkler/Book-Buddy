package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static model.Genre.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookCollectionTest {
    private BookCollection testBC;
    private Book testBookA;
    private Book testBookB;
    private Book testBookC;
    private Book testBookD;

    @BeforeEach
    public void runBefore() {
        testBC = new BookCollection();
        testBookA = new Book("Pride and Prejudice", "Jane Austen", classic);
        testBookB = new Book("Emma", "Jane Austen", classic);
        testBookC = new Book("The Secret History", "Donna Tartt", mystery);
        testBookD = new Book("The Handmaid's Tale", "Margaret Atwood", scifi);
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

        testBC.readBook(testBookA);
        assertEquals(1, books.size());
        assertEquals(testBookA, books.get(0));

        testBC.readBook(testBookB);
        testBC.readBook(testBookC);
        assertEquals(3, books.size());
        assertEquals(testBookA, books.get(0));
        assertEquals(testBookB, books.get(1));
        assertEquals(testBookC, books.get(2));
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
    public void testRemoveFavoriteBookSuccess() {
        List<Book> favBooks = testBC.getFavouriteBooks();

        testBC.readBook(testBookA);
        testBC.readBook(testBookB);
        testBC.addFavouriteBook(testBookA);
        testBC.addFavouriteBook(testBookB);
        assertTrue(testBC.removeFavouriteBook(testBookA));

        assertEquals(1, favBooks.size());
        assertEquals(testBookB, favBooks.get(0));

        assertTrue(testBC.removeFavouriteBook(testBookB));
        assertEquals(0, favBooks.size());
    }

    @Test
    public void testRemoveFavoriteBookFail() {
        List<Book> favBooks = testBC.getFavouriteBooks();

        assertFalse(testBC.removeFavouriteBook(testBookB));
        testBC.readBook(testBookA);
        testBC.addFavouriteBook(testBookA);
        assertFalse(testBC.removeFavouriteBook(testBookC));

        assertEquals(1, favBooks.size());
        assertEquals(testBookA, favBooks.get(0));
    }

    @Test
    public void testWantToReadBook() {
        List<Book> WTR = testBC.getWantToRead();

        testBC.wantToReadBook(testBookA);
        assertEquals(1, WTR.size());
        assertEquals(testBookA, WTR.get(0));

        testBC.wantToReadBook(testBookB);
        assertEquals(2, WTR.size());
        assertEquals(testBookA, WTR.get(0));
        assertEquals(testBookB, WTR.get(1));
    }

    @Test
    public void testRemoveFromWantToReadSuccess() {
        List<Book> WTR = testBC.getWantToRead();

        testBC.wantToReadBook(testBookA);
        testBC.wantToReadBook(testBookB);

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
        testBC.wantToReadBook(testBookA);
        assertFalse(testBC.removeFromWantToRead(testBookB));

        assertEquals(1, WTR.size());
        assertEquals(testBookA, WTR.get(0));
    }

    @Test
    public void testGetNumOfGenreEmpty() {
        assertEquals(0, testBC.getNumOfGenreRead(classic));
        assertEquals(0, testBC.getNumOfGenreRead(mystery));
    }

    @Test
    public void testGetNumOfGenreRead() {
        testBC.readBook(testBookA);
        testBC.readBook(testBookB);
        testBC.readBook(testBookC);
        assertEquals(2, testBC.getNumOfGenreRead(classic));
        assertEquals(1, testBC.getNumOfGenreRead(mystery));
        assertEquals(0, testBC.getNumOfGenreRead(romance));
    }

    @Test
    public void testGetTopGenre() {
        testBC.readBook(testBookC);
        assertEquals(mystery, testBC.getTopGenre());

        testBC.readBook(testBookA);
        testBC.readBook(testBookB);
        testBC.readBook(testBookD);
        assertEquals(classic, testBC.getTopGenre());
    }

    @Test
    public void testGetTopGenreEqual() {
        testBC.readBook(testBookC);
        testBC.readBook(testBookD);
        assertEquals(mystery, testBC.getTopGenre());
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

    @Test
    public void testGetBook() {
        testBC.readBook(testBookA);
        testBC.readBook(testBookB);
        testBC.readBook(testBookC);

        assertEquals(testBookA, testBC.getBook("Pride and Prejudice"));
        assertEquals(testBookB, testBC.getBook("Emma"));
        assertEquals(testBookC, testBC.getBook("The Secret History"));

        assertEquals("", testBC.getBook("Circe").getTitle());
        assertEquals("", testBC.getBook("Circe").getAuthor());
        assertEquals(none, testBC.getBook("Circe").getGenre());

    }

    @Test
    public void testGetBookEmpty() {
        assertEquals("", testBC.getBook("Crime and Punishment").getTitle());
        assertEquals("", testBC.getBook("Crime and Punishment").getAuthor());
        assertEquals(none, testBC.getBook("Crime and Punishment").getGenre());
    }
}