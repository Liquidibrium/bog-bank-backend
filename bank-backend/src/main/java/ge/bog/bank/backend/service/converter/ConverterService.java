package ge.bog.bank.backend.service.converter;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public interface ConverterService {

    // default currency gel
//    BigDecimal Convert(String currencyTo, BigDecimal amount);
//
//    // TODO make multi currency converter
//    BigDecimal Convert(String currencyFrom, String currencyTo, BigDecimal amount);

    Optional<BigDecimal> convert(String username, String currency, BigDecimal amount);

//    void convert(String username, String currency, BigDecimal amount);
}
