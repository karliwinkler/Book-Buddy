package persistence.tests;

import model.Book;
import model.BookCollection;
import model.Genre;

import static org.junit.Assert.assertEquals;

public class JsonTest {
    // effects: tests if given fields match given book
    protected void checkBook(String title, String author, Genre genre, int rating, String review, Book book) {
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(genre, book.getGenre());
        assertEquals(rating, book.getRating());
        assertEquals(review, book.getReview());
    }

    // effects: creates books and adds them to book collection for testing
    protected void initializeBookCollection(BookCollection bc) {
        Book favBook = new Book("test", "test", Genre.mystery);
        favBook.rateBook(5);
        favBook.reviewBook("Great book!");
        bc.readBook(favBook);
        bc.addFavouriteBook(favBook);
        bc.wantToReadBook(new Book("test2", "test2", Genre.scifi));
    }
}
