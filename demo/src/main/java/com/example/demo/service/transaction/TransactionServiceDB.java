package com.example.demo.service.transaction;

import com.example.demo.model.TransferDto;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceDB implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionServiceDB(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Boolean transferMoney(String usernameFrom, String usernameTo, Long amount) {
        boolean valid = // validate if usernameFrom is valid
                TransactionValidator.validateTransaction(usernameFrom, usernameTo, amount);
        if (valid){
            // main logic
        }
        return null;
    }

    @Override
    public Boolean transferMoney(TransferDto transferDto) {
        return transferMoney(transferDto.getUsernameFrom(),
                transferDto.getUsernameTo(),
                transferDto.getAmount());
    }
}
