package ge.bog.currencyconverter.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public interface ConverterService {

    Optional<BigDecimal> exchange(String currency, BigDecimal amount);
}
