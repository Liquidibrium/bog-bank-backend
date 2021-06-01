package com.example.demo.controller;

import com.example.demo.model.TransferDto;
import com.example.demo.service.transaction.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    //    TODO  remove
    @GetMapping("/{usernameFrom}")
    private ResponseEntity<String> makeTransaction(@PathVariable String usernameFrom,
                                                   @RequestParam String usernameTo,
                                                   @RequestParam Long amount) {

        Boolean res = transactionService.transferMoney(usernameFrom, usernameTo, amount);

        return new ResponseEntity<>(res.toString(), HttpStatus.OK);
    }

    @PostMapping()
    private ResponseEntity<String> makeTransaction(@RequestBody TransferDto transferDto) {

        Boolean res = transactionService.transferMoney(transferDto);

        return new ResponseEntity<>(res.toString(), HttpStatus.OK);
    }

}
