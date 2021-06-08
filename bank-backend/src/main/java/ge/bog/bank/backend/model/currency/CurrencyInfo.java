package ge.bog.bank.backend.model.currency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

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

}