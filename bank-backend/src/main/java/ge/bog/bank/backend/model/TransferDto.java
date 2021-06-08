package ge.bog.bank.backend.model;

import ge.bog.bank.backend.entitiy.TransactionEntity;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransferDto implements Serializable {
    private Long accIDFrom;
    private Long accIDTo;
    private BigDecimal amount;

    public static TransferDto toDto(TransactionEntity transactionEntity) {
        return TransferDto.builder()
                .accIDFrom(transactionEntity.getFromAcc().getAccountId())
                .accIDTo(transactionEntity.getToAcc().getAccountId())
                .amount(transactionEntity.getAmount())
                .build();
    }
}
