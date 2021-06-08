package ge.bog.bank.backend.service.transaction;

import ge.bog.bank.backend.entitiy.AccountEntity;
import ge.bog.bank.backend.entitiy.TransactionEntity;
import ge.bog.bank.backend.exception.AccountNotFoundException;
import ge.bog.bank.backend.exception.InvalidBankTransactionException;
import ge.bog.bank.backend.exception.NotEnoughMoneyException;
import ge.bog.bank.backend.model.TransferDto;
import ge.bog.bank.backend.repository.AccountRepository;
import ge.bog.bank.backend.repository.TransactionRepository;
import ge.bog.bank.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransactionServiceDB implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionServiceDB(TransactionRepository transactionRepository, UserRepository userRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public TransactionEntity transferMoney(Long accIDFrom, Long accIDTo, BigDecimal amount) {
        boolean valid = // validate if usernameFrom is valid
                TransactionValidator.validateTransaction(accIDFrom, accIDTo, amount);
        if (valid) {
            Optional<AccountEntity> optionalAccountFrom = accountRepository.findById(accIDFrom);
            if (optionalAccountFrom.isPresent()) {
                AccountEntity accFrom = optionalAccountFrom.get();
                if (accFrom.getBalance().compareTo(amount) >= 0) {
                    Optional<AccountEntity> optionalAccTo = accountRepository.findById(accIDTo);
                    if (optionalAccTo.isPresent()) {
                        AccountEntity accTo = optionalAccTo.get();
                        accFrom.setBalance(accFrom.getBalance().subtract(amount));
                        accTo.setBalance(accTo.getBalance().add(amount));
                        TransactionEntity transaction = new TransactionEntity(accFrom, accTo, amount);
                        // does this needed ?? TODO
//                        accFrom.addTransactionFrom(transaction);
//                        accTo.addTransactionTo(transaction);
//                        accountRepository.save(accFrom);
//                        accountRepository.save(accTo);
                        transactionRepository.save(transaction);
                        return transaction;
                    }
                    throw new AccountNotFoundException(accIDTo);
                }
                throw new NotEnoughMoneyException(accIDFrom, amount, accFrom.getCurrency());
            }
            throw new AccountNotFoundException(accIDFrom);
        }
        throw new InvalidBankTransactionException(accIDFrom, accIDTo, amount);
    }

    @Override
    public TransactionEntity transferMoney(TransferDto transferDto) {
        return transferMoney(transferDto.getAccIDFrom(),
                transferDto.getAccIDTo(),
                transferDto.getAmount());
    }

    @Override
    public TransactionEntity addMoneyToAccount(Long accIDTo, BigDecimal amount) {
        boolean valid = // validate if usernameFrom is valid
                TransactionValidator.validateTransaction(accIDTo, accIDTo, amount);
        if (valid) {
            Optional<AccountEntity> optionalAccountTo = accountRepository.findById(accIDTo);
            if (optionalAccountTo.isPresent()) {
                AccountEntity accTo = optionalAccountTo.get();
                accTo.setBalance(accTo.getBalance().add(amount));
                TransactionEntity transaction = new TransactionEntity(accTo, accTo, amount); // this may be error-prone
                transactionRepository.save(transaction);
                return transaction;
            }
            throw new AccountNotFoundException(accIDTo);
        }
        throw new InvalidBankTransactionException(accIDTo, accIDTo, amount);
    }

}
