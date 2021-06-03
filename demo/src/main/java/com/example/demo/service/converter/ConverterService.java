package com.example.demo.service.converter;

import java.math.BigDecimal;
import java.util.Currency;

public interface ConverterService {

    // default currency gel
    BigDecimal Convert(String currencyTo, BigDecimal amount);

    // TODO make multi currency converter
    BigDecimal Convert(String currencyFrom, String currencyTo, BigDecimal amount);
}
