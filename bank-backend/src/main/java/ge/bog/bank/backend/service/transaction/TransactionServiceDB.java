package ge.bog.bank.backend.service.transaction;

import ge.bog.bank.backend.entitiy.AccountEntity;
import ge.bog.bank.backend.model.TransferDto;
import ge.bog.bank.backend.repository.TransactionRepository;
import ge.bog.bank.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionServiceDB implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionServiceDB(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Boolean transferMoney(AccountEntity usernameFrom, AccountEntity usernameTo, BigDecimal amount) {
        boolean valid = // validate if usernameFrom is valid
                TransactionValidator.validateTransaction(usernameFrom, usernameTo, amount);
        if (valid) {
            // main logic

        }
        return null;
    }

    @Override
    public Boolean transferMoney(TransferDto transferDto) {
        return transferMoney(transferDto.getAccFrom(),
                transferDto.getAccTo(),
                transferDto.getAmount());
    }
}
