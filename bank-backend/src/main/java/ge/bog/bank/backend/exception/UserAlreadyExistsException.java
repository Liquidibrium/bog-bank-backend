package ge.bog.bank.backend.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String username) {
        super("user: %s already exists".formatted(username));
    }
}
