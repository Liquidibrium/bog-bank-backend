package ge.bog.bank.backend.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

@Slf4j
public class HashUtil {
    private static String SHA256 = "SHA-256";

    public static Optional<String> getSHA256(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance(SHA256);
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            return Optional.ofNullable(Base64.getEncoder().encodeToString(hash));
        } catch (NoSuchAlgorithmException e) {
            log.warn(e.getMessage());
        }
        return Optional.empty();
    }

}
