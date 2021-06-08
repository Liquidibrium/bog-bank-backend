package ge.bog.bank.backend.service.account;


import ge.bog.bank.backend.model.AccountDto;

import java.util.Set;

public interface AccountService {

    Set<AccountDto> getUserAccounts(String username);

    AccountDto addAccount(String username, String currency);

    AccountDto getAccount(String username, Long accId);
}
