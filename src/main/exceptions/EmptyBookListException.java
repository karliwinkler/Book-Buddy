package exceptions;

// represents an exception that occurs when an action that requires a non-empty book list
// is attempted on an empty list of books
public class EmptyBookListException extends Exception {
    public EmptyBookListException() {
        super("Your book list is empty. Add a book to perform this action.");
    }

    public EmptyBookListException(String msg) {
        super(msg);
    }
}
