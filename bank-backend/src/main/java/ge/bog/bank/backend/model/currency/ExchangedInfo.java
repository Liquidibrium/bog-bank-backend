package ge.bog.bank.backend.model.currency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ExchangedInfo {
    private CurrencyInfo info;
    private BigDecimal amountFrom;
    private BigDecimal exchangedAmount;

}