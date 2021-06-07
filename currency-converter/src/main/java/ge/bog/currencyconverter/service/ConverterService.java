package ge.bog.currencyconverter.service;

import ge.bog.currencyconverter.model.CurrencyInfo;
import ge.bog.currencyconverter.model.ExchangedInfo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public interface ConverterService {

    Optional<ExchangedInfo> exchange(String currency, BigDecimal amount);

    List<CurrencyInfo> getAllCurrencyInfo();

}
