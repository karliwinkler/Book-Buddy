package persistence;

import model.Book;
import model.BookCollection;
import model.Genre;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// represents reader that reads stored book collection JSON data from file (citation: based on JsonSerializationDemo)
public class JsonReader {
    private String source;

    //effects: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //effects: reads book collection from file and returns it
    public BookCollection read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBookCollection(jsonObject);
    }

    //effects: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //effects: parses book collection from JSON and returns it
    private BookCollection parseBookCollection(JSONObject jsonObject) {
        BookCollection bc = new BookCollection();
        addReadBooks(bc, jsonObject);
        addWantToRead(bc, jsonObject);
        addFavouriteBooks(bc, jsonObject);
        return bc;
    }

    // modifies: book collection
    // effects: parses read books from JSON object and adds them to book collection
    private void addReadBooks(BookCollection bc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("read books");
        for (Object json : jsonArray) {
            JSONObject nextBook = (JSONObject) json;
            addBookToRead(bc, nextBook);
        }
    }

    // modifies: book collection
    // effects: parses want to read books from JSON object and adds them to book collection
    private void addWantToRead(BookCollection bc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("want to read");
        for (Object json : jsonArray) {
            JSONObject nextBook = (JSONObject) json;
            addBookToWantToRead(bc, nextBook);
        }
    }

    // modifies: book collection
    // effects: parses favourite books from JSON object and adds them to book collection
    private void addFavouriteBooks(BookCollection bc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("favourite books");
        for (Object json : jsonArray) {
            JSONObject nextBook = (JSONObject) json;
            addBookToFavs(bc, nextBook);
        }
    }

    // modifies: book collection
    // effects: parses book from JSON object and adds it to read books list in book collection
    private void addBookToRead(BookCollection bc, JSONObject jsonObject) {
        String name = jsonObject.getString("title");
        String author = jsonObject.getString("author");
        Genre genre = Genre.valueOf(jsonObject.getString("genre"));
        int rating = jsonObject.getInt("rating");
        String review = jsonObject.getString("review");

        Book book = new Book(name, author, genre);
        book.rateBook(rating);
        book.reviewBook(review);
        bc.readBook(book);
    }

    // modifies: book collection
    // effects: parses book from JSON object and adds it to want to read list in book collection
    private void addBookToWantToRead(BookCollection bc, JSONObject jsonObject) {
        String name = jsonObject.getString("title");
        String author = jsonObject.getString("author");
        Genre genre = Genre.valueOf(jsonObject.getString("genre"));
        int rating = jsonObject.getInt("rating");
        String review = jsonObject.getString("review");

        Book book = new Book(name, author, genre);
        book.rateBook(rating);
        book.reviewBook(review);
        bc.wantToReadBook(book);
    }

    // modifies: book collection
    // effects: parses book from JSON object and adds it to favourites list in book collection
    private void addBookToFavs(BookCollection bc, JSONObject jsonObject) {
        String name = jsonObject.getString("title");
        String author = jsonObject.getString("author");
        Genre genre = Genre.valueOf(jsonObject.getString("genre"));
        int rating = jsonObject.getInt("rating");
        String review = jsonObject.getString("review");

        Book book = new Book(name, author, genre);
        book.rateBook(rating);
        book.reviewBook(review);
        bc.addFavouriteBook(book);
    }

}
