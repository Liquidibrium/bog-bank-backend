package ge.bog.bank.backend.controller;

import ge.bog.bank.backend.service.converter.ConverterService;
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

    @GetMapping("/{username}/")
    public ResponseEntity<BigDecimal> convertCurrency(@PathVariable String username,
                                                      @RequestParam String currency,
                                                      @RequestParam BigDecimal amount) {

        Optional<BigDecimal> convert = converterService.convert(username, currency, amount);
        return convert.map(bigDecimal -> {
            log.info("USER:%s converted %s %s from GEL".formatted(username, currency, amount.toString()));
            return new ResponseEntity<>(bigDecimal, HttpStatus.OK);
        })
                .orElseGet(() -> {
                    log.info("could not convert %s %s from GEL for USER:%s".formatted(currency, amount.toString(), username));
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                });
    }

}
