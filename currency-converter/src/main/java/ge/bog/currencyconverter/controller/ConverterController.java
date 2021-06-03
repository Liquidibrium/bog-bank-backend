package ge.bog.currencyconverter.controller;


import ge.bog.currencyconverter.service.ConverterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/convert")
@Slf4j
public class ConverterController {

    private final ConverterService converterService;

    public ConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }

    // TODO exchange other currencies too
    // can be exchanged only GEL
    @GetMapping("/{currency}")
    public ResponseEntity<BigDecimal> getConvert(@PathVariable String currency,
                                                 @RequestParam(defaultValue = "1") BigDecimal amount) {
        log.info("GET Request - CURRENCY:%s AMOUNT:%s".formatted(currency, amount.toString()));
        Optional<BigDecimal> exchanged = converterService.exchange(currency, amount);
        return exchanged.map(bigDecimal -> new ResponseEntity<>(bigDecimal, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));

    }
}
