package ge.bog.currencyconverter.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangedInfo {
    private CurrencyInfo info;
    private BigDecimal amountFrom;
    private BigDecimal exchangedAmount;

    public ExchangedInfo(CurrencyInfo info, BigDecimal amountFrom, BigDecimal exchanged) {
        this.info = info;
        this.amountFrom = amountFrom;
        this.exchangedAmount = exchanged;
    }
}
