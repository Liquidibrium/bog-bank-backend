package ge.bog.bank.backend.controller;

import ge.bog.bank.backend.exception.InvalidBankTransactionException;
import ge.bog.bank.backend.exception.NotEnoughMoneyException;
import ge.bog.bank.backend.exception.UserNotFoundException;
import ge.bog.bank.backend.model.TransferDto;
import ge.bog.bank.backend.service.transaction.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/transaction")
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping()
    private ResponseEntity<TransferDto> makeTransaction(@RequestBody TransferDto transferDto) {
        try {
            TransferDto transaction = transactionService.transferMoney(transferDto);
            log.info("successfully transferred money ");
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } catch (UserNotFoundException | NotEnoughMoneyException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (InvalidBankTransactionException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("failed to make transaction ");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/acc/{accountId}/")
    private ResponseEntity<TransferDto> addMoneyToAccount(@PathVariable Long accountId, @RequestParam BigDecimal amount) {
        try {
            TransferDto transaction = transactionService.addMoneyToAccount(accountId, amount);
            log.info("successfully transferred money ");
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (InvalidBankTransactionException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("failed to make transaction ");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
