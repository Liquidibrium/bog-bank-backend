package ge.bog.bank.backend.email;

public interface EmailSender {
    void send(String to, String subject, String text) throws IllegalStateException;

    default void sendWelcomeMail(String to, String username) throws IllegalStateException {
    }
}
