package exceptions;

// represents an exception that occurs when printing event log
public class LogException extends Exception {
    public LogException() {
        super("Error printing log");
    }

    public LogException(String msg) {
        super(msg);
    }
}
