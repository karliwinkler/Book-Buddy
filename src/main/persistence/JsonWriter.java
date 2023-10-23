package persistence;

import model.BookCollection;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// represents writer that writes JSON of book collection to file (citation: based on JsonSerializationDemo)
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // effects: constructs writer to write book collection to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // effects: opens writer and throws FileNotFoundException if destination file can't be opened
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // modifies: this
    // effects: writes JSON representation of book collection to file
    public void write(BookCollection bc) {
        JSONObject json = bc.toJson();
        saveToFile(json.toString(TAB));
    }

    // modifies: this
    // effects: closes writer
    public void close() {
        writer.close();
    }

    // modifies: this
    // effects: writes string to file
    public void saveToFile(String json) {
        writer.print(json);
    }
}
