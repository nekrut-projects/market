package gb.market.exceptions;

public class IncorrectUserDataException extends RuntimeException{
    public IncorrectUserDataException(String message) {
        super(message);
    }
}
