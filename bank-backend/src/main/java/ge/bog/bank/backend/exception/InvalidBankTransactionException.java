package ge.bog.bank.backend.exception;

import java.math.BigDecimal;

public class InvalidBankTransactionException extends  RuntimeException {
    public InvalidBankTransactionException(Long accIDFrom, Long accIDTo, BigDecimal amount) {
        super("transaction is not valid from %s to %s amount%s"
        .formatted(accIDFrom,accIDTo,amount));
    }
}
