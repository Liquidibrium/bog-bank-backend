package ge.bog.currencyconverter.service;

import ge.bog.currencyconverter.model.CurrencyInfo;
import ge.bog.currencyconverter.util.RssParser;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class ConverterServiceImpl implements ConverterService {

    private final RssParser rssParser;

    public ConverterServiceImpl(RssParser rssParser) {
        this.rssParser = rssParser;
    }

    public Optional<BigDecimal> exchange(String currency, BigDecimal amount) {
        Optional<CurrencyInfo> currencyInfoOptional = rssParser.getCurrencyRate(currency.toUpperCase());
        if (currencyInfoOptional.isPresent()) {
            Long quantity = currencyInfoOptional.get().getQuantity();
            BigDecimal rate = currencyInfoOptional.get().getVal();
            BigDecimal exchanged = amount.multiply(rate).divide(BigDecimal.valueOf(quantity),
                    4, RoundingMode.FLOOR);
            return Optional.of(exchanged);
        }
        return Optional.empty();
    }
}
