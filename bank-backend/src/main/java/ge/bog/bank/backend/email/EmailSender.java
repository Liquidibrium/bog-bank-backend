package ge.bog.bank.backend.email;

public interface EmailSender {
    void send(String to, String subject, String text);

    default void sendWelcomeMail(String to, String username) {
    }
}
