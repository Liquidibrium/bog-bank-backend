package ge.bog.bank.backend.service.transaction;

import ge.bog.bank.backend.entitiy.AccountEntity;
import ge.bog.bank.backend.model.TransferDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface TransactionService {

    Boolean transferMoney(AccountEntity usernameFrom, AccountEntity usernameTo, BigDecimal amount);

    Boolean transferMoney(TransferDto transferDto);
}
