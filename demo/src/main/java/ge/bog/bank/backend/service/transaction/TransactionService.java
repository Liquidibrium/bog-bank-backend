package ge.bog.bank.backend.service.transaction;

import ge.bog.bank.backend.model.TransferDto;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {

    Boolean transferMoney(String usernameFrom, String usernameTo, Long amount);

    Boolean transferMoney(TransferDto transferDto);
}
