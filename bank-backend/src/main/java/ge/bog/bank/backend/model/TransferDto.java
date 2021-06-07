package ge.bog.bank.backend.model;

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
}
