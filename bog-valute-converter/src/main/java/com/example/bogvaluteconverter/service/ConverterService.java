package com.example.bogvaluteconverter.service;

import com.example.bogvaluteconverter.util.RssParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ConverterService {

    private final
    RssParser rssParser;

    public ConverterService(RssParser rssParser) {
        this.rssParser = rssParser;
    }

    public Optional<BigDecimal> exchange(String currency, BigDecimal amount) {
        return Optional.of(rssParser.getCurrencyRate(currency).multiply(amount));
    }
}
