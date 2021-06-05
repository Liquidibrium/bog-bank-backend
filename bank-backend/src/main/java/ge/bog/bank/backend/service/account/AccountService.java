package ge.bog.bank.backend.service.account;


import ge.bog.bank.backend.entitiy.AccountEntity;

import java.util.Set;

public interface AccountService {

    Set<AccountEntity> getUserAccounts(String username);

    AccountEntity addAccount(String username, String currency);

    AccountEntity getAccount(String username, Long accId);
}
