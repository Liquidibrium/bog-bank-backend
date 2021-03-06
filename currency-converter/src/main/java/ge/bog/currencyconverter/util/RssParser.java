package ge.bog.currencyconverter.util;

import ge.bog.currencyconverter.model.CurrencyInfo;

import java.util.List;
import java.util.Optional;


public interface RssParser {

    Optional<CurrencyInfo> getCurrencyRate(String currency);

    List<CurrencyInfo> getCurrencyRates();
}
