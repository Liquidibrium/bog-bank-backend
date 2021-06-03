package ge.bog.currencyconverter.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrencyInfo {
    private String name;
    private String description;
    private Long quantity;
    private BigDecimal val;
}