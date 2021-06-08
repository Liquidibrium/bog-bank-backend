package ge.bog.bank.backend.exception;

import java.math.BigDecimal;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(Long accIDFrom, BigDecimal amount, String currency) {
        super("Sender dont have enough money (%s %s) on Account %s"
                .formatted(amount.toString(),currency,accIDFrom));
    }
}
