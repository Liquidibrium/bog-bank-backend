package ge.bog.bank.backend.email;

import ge.bog.bank.backend.exception.InvalidMailException;

public class EmailValidator {

    public static void validate(String email) throws InvalidMailException {
        if (email.contains("@")) {
            return;
        }
        throw new InvalidMailException(email);
    }
}
