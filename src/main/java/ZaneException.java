public class ZaneException extends Exception{
    protected String message;

    public ZaneException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
