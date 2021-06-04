package ge.bog.bank.backend.exception;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String username) {
        super("Invalid username: " + username);
    }
}
