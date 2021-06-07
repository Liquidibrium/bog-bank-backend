package ge.bog.currencyconverter.controller;


import ge.bog.currencyconverter.model.CurrencyInfo;
import ge.bog.currencyconverter.model.ExchangedInfo;
import ge.bog.currencyconverter.service.ConverterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/convert")
@Slf4j
public class ConverterController {

    private final ConverterService converterService;

    public ConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }

    // can be exchanged only GEL
    @GetMapping("{currency}")
    public ResponseEntity<ExchangedInfo> getConvert(@PathVariable String currency,
                                                    @RequestParam(defaultValue = "1") BigDecimal amount) {
        log.info("GET Request - CURRENCY:%s AMOUNT:%s".formatted(currency, amount.toString()));
        Optional<ExchangedInfo> exchanged = converterService.exchange(currency, amount);
        return exchanged.map(exc -> new ResponseEntity<>(exc, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));

    }

    @GetMapping()
    public ResponseEntity<List<CurrencyInfo>> getAllCurrencyInfo() {
        List<CurrencyInfo> exchanged = converterService.getAllCurrencyInfo();
        return new ResponseEntity<>(exchanged, HttpStatus.OK);

    }
}
