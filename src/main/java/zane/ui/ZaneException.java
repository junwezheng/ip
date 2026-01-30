package zane.ui;

/**
 * Exception class for the Zane application.
 * Thrown when an error occurs in the Zane application.
 */
public class ZaneException extends RuntimeException {
    /**
     * Constructor for the ZaneException class.
     * @param message The error message to display.
     */
    public ZaneException(String message) {
        super(message);
    }
}
