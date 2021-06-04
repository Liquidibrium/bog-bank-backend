package ge.bog.bank.backend.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(Long accId) {
        super("Account not found by Id %s".formatted(accId.toString()));
    }
}
