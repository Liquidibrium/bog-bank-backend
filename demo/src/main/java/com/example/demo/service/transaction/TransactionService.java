package com.example.demo.service.transaction;

import com.example.demo.model.TransferDto;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {

    Boolean transferMoney(String usernameFrom,String usernameTo, Long amount);

    Boolean transferMoney(TransferDto transferDto);
}
