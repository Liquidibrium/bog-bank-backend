package ge.bog.bank.backend.service.transaction;

import ge.bog.bank.backend.entitiy.AccountEntity;
import ge.bog.bank.backend.entitiy.TransactionEntity;
import ge.bog.bank.backend.model.TransferDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface TransactionService {

    TransactionEntity transferMoney(Long usernameFrom, Long usernameTo, BigDecimal amount);

    TransactionEntity transferMoney(TransferDto transferDto);
}
