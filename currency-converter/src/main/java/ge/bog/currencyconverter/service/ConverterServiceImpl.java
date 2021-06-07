package ge.bog.currencyconverter.service;

import ge.bog.currencyconverter.model.CurrencyInfo;
import ge.bog.currencyconverter.model.ExchangedInfo;
import ge.bog.currencyconverter.util.RssParser;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class ConverterServiceImpl implements ConverterService {

    private final static int SCALE = 4; // *.XXXX
    private final RssParser rssParser;

    public ConverterServiceImpl(RssParser rssParser) {
        this.rssParser = rssParser;
    }

    public Optional<ExchangedInfo> exchange(String currency, BigDecimal amount) {
        Optional<CurrencyInfo> currencyInfoOptional = rssParser.getCurrencyRate(currency.toUpperCase());
        if (currencyInfoOptional.isPresent()) {
            CurrencyInfo curr = currencyInfoOptional.get();
            Long quantity = curr.getQuantity();
            BigDecimal rate = curr.getVal();
            BigDecimal exchanged = amount.multiply(rate).divide(BigDecimal.valueOf(quantity),
                    SCALE, RoundingMode.FLOOR);
            return Optional.of(new ExchangedInfo(curr, amount, exchanged));
        }
        return Optional.empty();
    }

    @Override
    public List<CurrencyInfo> getAllCurrencyInfo() {
        return rssParser.getCurrencyRates();
    }
}
