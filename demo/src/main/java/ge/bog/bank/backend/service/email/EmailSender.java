package ge.bog.bank.backend.service.email;

public interface EmailSender {
    void send(String to, String email);

}
