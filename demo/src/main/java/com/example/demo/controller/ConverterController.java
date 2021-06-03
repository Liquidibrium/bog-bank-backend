package com.example.demo.controller;

import com.example.demo.service.converter.ConverterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/convert")
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
        return convert.map(bigDecimal -> new ResponseEntity<>(bigDecimal, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

}
