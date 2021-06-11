package ge.bog.bank.backend.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
class EmailSenderImpl implements EmailSender {

    private final static Logger LOGGER = LoggerFactory
            .getLogger(EmailSenderImpl.class);
    private static final String ERROR_STRING = "failed to send email ";
    private static final String ENCODING = "utf-8";
    private static final boolean TEST_MODE = true;  // actually email is not going to be sent
    private static final String WELCOMING_SUBJECT = "Bank Registration Email";

    @Value("${my.email.name}")
    private static String mail;

    private final JavaMailSender mailSender;

    EmailSenderImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async // Async can have bugs in future, for using this code
    @Override
    public void send(String to, String subject, String text) throws IllegalStateException {
        if (TEST_MODE) return;
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, ENCODING);
            helper.setText(text, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(mail); // change to default account , form properties
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error(ERROR_STRING + to);
            throw new IllegalStateException(ERROR_STRING);
        }
    }


    @Override
    public void sendWelcomeMail(String to, String username) throws IllegalStateException {
        send(to, WELCOMING_SUBJECT, "Dear %s, welcome to Bank".formatted(username));
    }
}
