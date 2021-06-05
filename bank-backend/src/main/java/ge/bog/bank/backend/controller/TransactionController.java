package ge.bog.bank.backend.controller;

import ge.bog.bank.backend.entitiy.TransactionEntity;
import ge.bog.bank.backend.exception.InvalidBankTransactionException;
import ge.bog.bank.backend.exception.InvalidUserException;
import ge.bog.bank.backend.exception.NotEnoughMoneyException;
import ge.bog.bank.backend.exception.UserNotFoundException;
import ge.bog.bank.backend.model.TransferDto;
import ge.bog.bank.backend.service.transaction.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping()
    private ResponseEntity<TransactionEntity> makeTransaction(@RequestBody TransferDto transferDto) {
        try {
            TransactionEntity transactionEntity = transactionService.transferMoney(transferDto);
            log.info("successfully transferred money ");
            return new ResponseEntity<>(transactionEntity, HttpStatus.OK);
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

}
