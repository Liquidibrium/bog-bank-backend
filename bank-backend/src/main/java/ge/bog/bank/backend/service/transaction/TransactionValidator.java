package ge.bog.bank.backend.service.transaction;

import java.math.BigDecimal;

public class TransactionValidator {

    public static Boolean validateTransaction(Long usernameFrom, Long usernameTo, BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }

}
