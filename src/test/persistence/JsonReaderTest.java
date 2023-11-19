package persistence;

import model.Book;
import model.BookCollection;
import model.Genre;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BookCollection bc = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyBookCollection() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBookCollection.json");
        try {
            BookCollection bc = reader.read();
            List<Book> readBooks = bc.getReadBooks();
            List<Book> wantToRead = bc.getWantToRead();
            List<Book> favBooks = bc.getFavouriteBooks();
            assertEquals(0, readBooks.size());
            assertEquals(0, wantToRead.size());
            assertEquals(0, favBooks.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneral() {
        JsonReader reader = new JsonReader("./data/testReaderBookCollection.json");
        try {
            BookCollection bc = reader.read();
            List<Book> readBooks = bc.getReadBooks();
            List<Book> wantToRead = bc.getWantToRead();
            List<Book> favBooks = bc.getFavouriteBooks();
            assertEquals(1, readBooks.size());
            assertEquals(0, wantToRead.size());
            assertEquals(1, favBooks.size());
            checkBook("The Secret History", "Donna Tartt", Genre.mystery, 5,
                    "Great book!", readBooks.get(0));
            checkBook("The Secret History", "Donna Tartt", Genre.mystery, 5,
                    "Great book!", favBooks.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }
}
