package robots.exception;

public class InvalidPositionException extends Exception {

    private static final long serialVersionUID = 7311356009002553845L;

    public InvalidPositionException(String message) {
        super(message);
    }

}
