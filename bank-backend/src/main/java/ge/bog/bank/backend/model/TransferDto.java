package ge.bog.bank.backend.model;

import ge.bog.bank.backend.entitiy.AccountEntity;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransferDto implements Serializable {

    private AccountEntity accFrom;
    private AccountEntity accTo;
    private BigDecimal amount;
    private LocalDateTime time;

}
