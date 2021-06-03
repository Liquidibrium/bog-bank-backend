package ge.bog.bank.backend.controller;

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


    //    TODO  remove
    @GetMapping("/{usernameFrom}")
    private ResponseEntity<String> makeTransaction(@PathVariable String usernameFrom,
                                                   @RequestParam String usernameTo,
                                                   @RequestParam Long amount) {
        try {
            Boolean res = transactionService.transferMoney(usernameFrom, usernameTo, amount);
            log.info("successfully transferred money ");
            return new ResponseEntity<>(res.toString(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("failed to make transaction ");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    private ResponseEntity<String> makeTransaction(@RequestBody TransferDto transferDto) {
        try {
            Boolean res = transactionService.transferMoney(transferDto);
            log.info("successfully transferred money ");
            return new ResponseEntity<>(res.toString(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("failed to make transaction ");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
