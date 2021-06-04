package ge.bog.bank.backend.service.account;

import ge.bog.bank.backend.entitiy.AccountEntity;
import ge.bog.bank.backend.entitiy.UserEntity;
import ge.bog.bank.backend.exception.AccountNotFoundException;
import ge.bog.bank.backend.exception.InvalidCurrencyException;
import ge.bog.bank.backend.exception.InvalidUserException;
import ge.bog.bank.backend.exception.UserNotFoundException;
import ge.bog.bank.backend.model.AccountDto;
import ge.bog.bank.backend.model.UserDto;
import ge.bog.bank.backend.repository.AccountRepository;
import ge.bog.bank.backend.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;

@Service
public class AccountServiceDB implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountServiceDB(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Set<AccountEntity> getUserAccounts(@NotNull String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get()
                    .getAccountSet();
//            return user.get()
//                    .getAccountSet().stream()
//                    .map(AccountDto::EntityToDto)
//                    .collect(Collectors.toList());
        }
        throw new UserNotFoundException(username);
    }

    @Override
    public AccountEntity addAccount(String username, String currency) throws InvalidCurrencyException , UserNotFoundException{
        CurrencyValidator.validate(currency);
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);
        if (userEntityOptional.isPresent()) {
            UserEntity user = userEntityOptional.get();
            AccountEntity acc = new AccountEntity(currency, user);
            accountRepository.save(acc);
            return  acc;
        }
        throw new UserNotFoundException(username);
    }

    @Override
    public AccountEntity getAccount(String username, Long accId) {
        Optional<AccountEntity> accountEntityOptional = accountRepository.findById(accId);
        if(accountEntityOptional.isPresent()){
            AccountEntity account = accountEntityOptional.get();
            if(!account.getUser().getUsername().equals(username)){
                throw new InvalidUserException(username);
            }
            return  account;
        }
        throw new AccountNotFoundException(accId);
    }
}