package model;

import exceptions.EmptyBookListException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

import static model.Genre.*;

// represents collection of user's books, including books they've read recently,
// their favourite books, and books they want to read
public class BookCollection implements Writable {
    private List<Book> readBooks;
    private List<Book> favouriteBooks;
    private List<Book> wantToRead;

    // effects: constructs empty list of books read, empty list of favourite books, and empty want-to-read list
    public BookCollection() {
        readBooks = new ArrayList<>();
        favouriteBooks = new ArrayList<>();
        wantToRead = new ArrayList<>();
    }

    // modifies: this
    // effects: adds book to read books list
    public void readBook(Book book) {
        readBooks.add(book);
        EventLog.getInstance().logEvent(new Event("Book added to read list."));
    }

    // modifies: this
    // effects: for printing event log after loading saved data - doesn't log event
    public void readBookNoLog(Book book) {
        readBooks.add(book);
    }

    // modifies: this
    // effects: if book is in read books list, adds book to favourites list and returns true,
    // otherwise returns false.
    public boolean addFavouriteBook(Book book) {
        if (readBooks.contains(book)) {
            favouriteBooks.add(book);
            EventLog.getInstance().logEvent(new Event("Book added to favourites list."));
            return true;
        }
        return false;
    }

    // modifies: this
    // effects: for printing event log after loading saved data - doesn't log event
    public boolean addFavouriteBookNoLog(Book book) {
        if (readBooks.contains(book)) {
            favouriteBooks.add(book);
            return true;
        }
        return false;
    }

    // modifies: this
    // effects: if book is in favourites list, removes it from favourites list and returns true,
    // otherwise returns false.
    public boolean removeFavouriteBook(Book book) {
        if (favouriteBooks.contains(book)) {
            favouriteBooks.remove(book);
            return true;
        }
        return false;
    }

    // modifies: this
    // effects: adds book to list of books user would like to read.
    public void wantToReadBook(Book book) {
        wantToRead.add(book);
        EventLog.getInstance().logEvent(new Event("Book added to want to read list."));
    }

    // modifies: this
    // effects: for printing event log after loading saved data - doesn't log event
    public void wantToReadBookNoLog(Book book) {
        wantToRead.add(book);
    }


    // modifies: this
    // effects: removes book from list of books user would like to read and returns true.
    // if book is not in list, returns false
    public boolean removeFromWantToRead(Book book) {
        if (wantToRead.contains(book)) {
            wantToRead.remove(book);
            return true;
        }
        return false;
    }

    // effects: returns number of books user has read of given genre
    public int getNumOfGenreRead(Genre genre) {
        int totalGenre = 0;

        for (Book b : readBooks) {
            if (genre == b.getGenre()) {
                totalGenre++;
            }
        }
        return totalGenre;
    }

    // requires: read books list cannot be empty
    // effects: returns the genre user has read the most books of. If genres have equal amount,
    // the first genre that appears in read books list is returned. throws EmptyBookListException if
    // read books is empty.
    public Genre getTopGenre() throws EmptyBookListException {
        if (readBooks.isEmpty()) {
            throw new EmptyBookListException();
        }

        Genre topGenre = readBooks.get(0).getGenre();

        for (Book b : readBooks) {
            if (getNumOfGenreRead(topGenre) < getNumOfGenreRead(b.getGenre())) {
                topGenre = b.getGenre();
            }
        }
        return topGenre;
    }

    // effects: returns number of books user has read of given author
    public int getNumOfAuthorRead(String author) {
        int totalAuthor = 0;

        for (Book b : readBooks) {
            if (author.equals(b.getAuthor())) {
                totalAuthor++;
            }
        }
        return totalAuthor;
    }

    // effects: returns the author user has read the most books of. If authors are equal,
    // the first author that appears in read books list is returned.
    public String getTopAuthor() {
        String topAuth = readBooks.get(0).getAuthor();

        for (Book b : readBooks) {
            if (getNumOfAuthorRead(topAuth) < getNumOfAuthorRead(b.getAuthor())) {
                topAuth = b.getAuthor();
            }
        }
        return topAuth;
    }

    // effects: retrieves book with given title from read books list, if no book with title is found,
    // return book with empty title, author, and genre.
    public Book getBook(String title) {
        Book emptyBook = new Book("","", none);

        for (Book b : readBooks) {
            if (title.equals(b.getTitle())) {
                return b;
            }
        }
        return emptyBook;
    }

    @Override
    public JSONObject toJson() {
        EventLog.getInstance().logEvent(new Event("Book collection saved to file."));
        JSONObject json = new JSONObject();
        json.put("read books", listToJson(readBooks));
        json.put("want to read", listToJson(wantToRead));
        json.put("favourite books", listToJson(favouriteBooks));
        return json;
    }

    // effects: returns given book list as JSON array
    private JSONArray listToJson(List<Book> books) {
        JSONArray jsonarray = new JSONArray();

        for (Book b : books) {
            jsonarray.put(b.toJson());
        }
        return jsonarray;
    }

    //getters
    public List<Book> getReadBooks() {
        return readBooks;
    }

    public List<Book> getFavouriteBooks() {
        return favouriteBooks;
    }

    public List<Book> getWantToRead() {
        return wantToRead;
    }
}
