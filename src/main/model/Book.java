package model;


import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

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
            return true;
        }
        return false;
    }

    // modifies: this
    // effects: adds user's review to book
    public void reviewBook(String review) {
        this.review = review;
    }

    // effects: returns true if this has same fields as given book
    public boolean checkSameBook(Book other) {
        return this.title.equals(other.getTitle())
                && this.author.equals(other.getAuthor())
                && this.genre == other.getGenre()
                && this.rating == other.getRating()
                && this.review.equals(other.getReview());
    }

    // effects: returns true if given list of books contains this
    public boolean containsBook(List<Book> books) {
        for (Book next : books) {
            if (this.checkSameBook(next)) {
                return true;
            }
        }
        return false;
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
