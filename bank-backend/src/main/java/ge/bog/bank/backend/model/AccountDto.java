package ge.bog.bank.backend.model;

import ge.bog.bank.backend.entitiy.AccountEntity;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountDto {
    private Long accountId;
    private String username;
    private BigDecimal balance;
    private String currency;

    public static AccountDto EntityToDto(AccountEntity accountEntity) {
        return AccountDto.builder()
                .accountId(accountEntity.getAccountId())
                .username(accountEntity.getUser().getUsername())
                .balance(accountEntity.getBalance())
                .currency(accountEntity.getCurrency())
                .build();
    }

}
