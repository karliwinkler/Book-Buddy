package model;


import org.json.JSONObject;
import persistence.Writable;

import java.util.List;
import java.util.Objects;

// represents a book with title, author, genre, and user's rating and review
public class Book implements Writable {
    private String title;
    private String author;
    private Genre genre;
    private int rating;
    private String review;

    // effects: constructs book with given title, author, genre, and no review or rating
    public Book(String title, String author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.review = "no review yet.";
    }

    // modifies: this
    // effects: adds user's rating out of 5 stars to book and returns true.
    // if given rating is not 0 < rating <= 5, doesn't add rating and returns false.
    public boolean rateBook(int rating) {
        if (rating > 0 && rating <= 5) {
            this.rating = rating;
            EventLog.getInstance().logEvent(new Event("Book rated " + rating + " stars."));
            return true;
        }
        return false;
    }

    // modifies: this
    // effects: for printing event log after loading saved data - doesn't log event
    public boolean rateBookNoLog(int rating) {
        if (rating > 0 && rating <= 5) {
            this.rating = rating;
            return true;
        }
        return false;
    }

    // modifies: this
    // effects: adds user's review to book
    public void reviewBook(String review) {
        this.review = review;
        EventLog.getInstance().logEvent(new Event("Book reviewed."));
    }

    // modifies: this
    // effects: for printing event log after loading saved data - doesn't log event
    public void reviewBookNoLog(String review) {
        this.review = review;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }
        Book book = (Book) o;
        return rating == book.rating
                && Objects.equals(title, book.title)
                && Objects.equals(author, book.author)
                && genre == book.genre && Objects.equals(review, book.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, genre, rating, review);
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("author", author);
        json.put("genre", genre);
        json.put("rating", rating);
        json.put("review", review);
        return json;
    }

    // getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

}
