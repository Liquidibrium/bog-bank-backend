package ge.bog.bank.backend.controller;

import ge.bog.bank.backend.entitiy.AccountEntity;
import ge.bog.bank.backend.exception.AccountNotFoundException;
import ge.bog.bank.backend.exception.InvalidUserException;
import ge.bog.bank.backend.exception.UserNotFoundException;
import ge.bog.bank.backend.service.account.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("{username}/account/")
    public ResponseEntity<Set<AccountEntity>> getAllAccountForUser(@PathVariable String username) {
        try {
            Set<AccountEntity> accounts = accountService.getUserAccounts(username);
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            log.warn("user: %s NOT FOUND".formatted(username));
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{username}/account/{accId}")
    public ResponseEntity<AccountEntity> getAccount(@PathVariable String username,
                                                    @PathVariable Long accId) {
        try {
            AccountEntity accounts = accountService.getAccount(username, accId);
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            log.warn("account not found by Id: %s".formatted(accId.toString()));
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (InvalidUserException e) {
            log.warn("invalid user: %s".formatted(username));
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("{username}/account/")
    public ResponseEntity<AccountEntity> AddAccountForUser(@PathVariable String username,
                                                           @RequestParam String currency) {
        try {
            AccountEntity accounts = accountService.addAccount(username, currency);
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            log.warn("user: %s NOT FOUND".formatted(username));
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
