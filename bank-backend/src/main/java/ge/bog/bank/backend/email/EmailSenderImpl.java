package ge.bog.bank.backend.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
class EmailSenderImpl implements EmailSender {

    private final static Logger LOGGER = LoggerFactory
            .getLogger(EmailSenderImpl.class);
    private static final String ERROR_STRING = "failed to send email";
    private static final String ENCODING = "utf-8";
    @Value("${my.email.name}")
    private static String mail;

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String subject, String text) {
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
            LOGGER.error(ERROR_STRING, e);
            throw new IllegalStateException(ERROR_STRING);
        }
    }

    private static final String WELCOMING_SUBJECT = "Bank Registration Email";

    @Override
    public void sendWelcomeMail(String to, String username) {
        send(to,WELCOMING_SUBJECT,"Dear %s, welcome to Bank".formatted(username));
    }
}
