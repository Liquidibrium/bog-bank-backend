package ge.bog.bank.backend.model.currency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

import static ge.bog.bank.backend.model.currency.CurrencyISO.GEL;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyInfo implements Serializable {
    //     default currency is GEL
    private String currencyTO;
    private String currencyFrom;
    private String description;
    private Long quantity;
    private BigDecimal val;

    public CurrencyInfo(String currencyFrom, String description, Long quantity, BigDecimal val) {
        this.currencyFrom = currencyFrom;
        this.description = description;
        this.quantity = quantity;
        this.val = val;
        this.currencyTO = GEL;
    }
}