package com.example.bogvaluteconverter.controller;


import com.example.bogvaluteconverter.service.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
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

    // TODO exchange other currencies too
    // can be exchanged only GEL
    @GetMapping("/{currency}")
    public ResponseEntity<BigDecimal> getConvert(@PathVariable String currency,
                                             @RequestParam(defaultValue = "0") BigDecimal amount) {
        Optional<BigDecimal> exchanged =  converterService.exchange(currency,amount);
        return exchanged.map(bigDecimal -> new ResponseEntity<>(bigDecimal, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));

    }
}
