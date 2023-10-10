package model;

import java.util.ArrayList;
import java.util.List;

// represents collection of user's books, including books they've read recently,
// their favourite books, and books they want to read
public class BookCollection {
    List<Book> readBooks;
    List<Book> favouriteBooks;
    List<Book> wantToRead;

    // effects: constructs empty list of books read, empty list of favourite books, and empty want-to-read list
    public BookCollection() {
        readBooks = new ArrayList<>();
        favouriteBooks = new ArrayList<>();
        wantToRead = new ArrayList<>();
    }

    // modifies: this
    // effects: adds book to read books list and removes it from want to read (if it's there)
    public void readBook(Book book) {
        if (wantToRead.contains(book)) {
            removeFromWantToRead(book);
        }
        readBooks.add(book);
    }

    // modifies: this
    // effects: if book is in read books list, adds book to favourites list and returns true,
    // otherwise returns false.
    public boolean addFavouriteBook(Book book) {
        if (readBooks.contains(book)) {
            favouriteBooks.add(book);
            return true;
        }
        return false;
    }

    // modifies: this
    // effects: adds book to list of books user would like to read and returns true.
    // if book is already in list or is in read books, returns false.
    public boolean wantToReadBook(Book book) {
        if (!wantToRead.contains(book) && !readBooks.contains(book)) {
            wantToRead.add(book);
            return true;
        }
        return false;
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
    // the first genre that appears in read books list is returned.
    public Genre getTopGenre() {
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
