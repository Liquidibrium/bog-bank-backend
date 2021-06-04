package ge.bog.bank.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ge.bog.bank.backend.entitiy.AccountEntity;
import ge.bog.bank.backend.entitiy.TransactionEntity;
import ge.bog.bank.backend.entitiy.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
public class AccountDto {

    private UserEntity user;

    private BigDecimal balance;

    private String currency;

    @JsonIgnore
    private Set<TransactionEntity> transactionEntitySetTo;

    @JsonIgnore
    private Set<TransactionEntity> transactionEntitySetFrom;

    public AccountDto EntityToDto(AccountEntity accountEntity) {
        return AccountDto.builder()
                .user(accountEntity.getUser())
                .balance(accountEntity.getBalance())
                .transactionEntitySetTo(accountEntity.getTransactionSetTo())
                .transactionEntitySetFrom(accountEntity.getTransactionSetFrom())
                .currency(accountEntity.getCurrency())
                .build();
    }

}
