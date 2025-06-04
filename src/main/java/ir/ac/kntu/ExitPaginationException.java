package ir.ac.kntu;

public class ExitPaginationException extends RuntimeException {
    public ExitPaginationException() {
        super();
    }

    public ExitPaginationException(String message) {
        super(message);
    }
}
