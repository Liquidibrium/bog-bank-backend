package ge.bog.bank.backend.controller;

import ge.bog.bank.backend.model.currency.ExchangedInfo;
import ge.bog.bank.backend.service.converter.ConverterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

import static ge.bog.bank.backend.model.currency.CurrencyISO.GEL;

@RestController
@RequestMapping("/api/convert/")
@Slf4j
public class ConverterController {

    private final ConverterService converterService;


    public ConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }

    @GetMapping("/{username}/")
    public ResponseEntity<ExchangedInfo> convertCurrency(@PathVariable String username,
                                                         @RequestParam String currency,
                                                         @RequestParam BigDecimal amount) {

        Optional<ExchangedInfo> convert = converterService.convert(username, currency, amount);
        return convert.map(exc -> {
            log.info("USER:%s converted %s %s to %s".formatted(username, currency, amount.toString(), GEL));
            return new ResponseEntity<>(exc, HttpStatus.OK);
        })
                .orElseGet(() -> {
                    log.info("could not convert %s %s to %s for USER:%s".formatted(currency, amount.toString(), GEL, username));
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                });
    }

}
