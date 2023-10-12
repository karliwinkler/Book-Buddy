package model;


// represents a book with title, author, genre, and user's rating and review
public class Book {
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
