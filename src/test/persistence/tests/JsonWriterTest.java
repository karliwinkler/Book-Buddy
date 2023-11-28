package persistence.tests;

import model.Book;
import model.BookCollection;
import model.Genre;
import org.junit.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            BookCollection bc = new BookCollection();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyBookCollection() {
        try {
            BookCollection bc = new BookCollection();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBookCollection.json");
            writer.open();
            writer.write(bc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBookCollection.json");
            bc = reader.read();
            assertEquals(0, bc.getReadBooks().size());
            assertEquals(0, bc.getWantToRead().size());
            assertEquals(0, bc.getFavouriteBooks().size());

        } catch (IOException e) {
            fail("Exception not excepted.");
        }

    }

    @Test
    public void testWriterGeneralBookCollection() {
        try {
            BookCollection bc = new BookCollection();
            initializeBookCollection(bc);
            JsonWriter writer = new JsonWriter("./data/testWriterBookCollection.json");
            writer.open();
            writer.write(bc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterBookCollection.json");
            bc = reader.read();
            List<Book> readBooks = bc.getReadBooks();
            List<Book> wantToRead = bc.getWantToRead();
            List<Book> favBooks = bc.getFavouriteBooks();
            assertEquals(1, readBooks.size());
            assertEquals(1, wantToRead.size());
            assertEquals(1, favBooks.size());
            checkBook("test", "test", Genre.mystery, 5, "Great book!", readBooks.get(0));
            checkBook("test2", "test2", Genre.scifi, 0, "no review yet.", wantToRead.get(0));
            checkBook("test", "test", Genre.mystery, 5, "Great book!", favBooks.get(0));

        } catch (IOException e) {
            fail("Exception not expected.");
        }
    }
}
