package ge.bog.bank.backend.exception;

public class InvalidMailException extends RuntimeException {
    public InvalidMailException(String email) {
        super("Invalid mail: %s".formatted(email));
    }
}
