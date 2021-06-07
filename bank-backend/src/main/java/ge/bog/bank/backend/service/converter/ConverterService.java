package ge.bog.bank.backend.service.converter;

import org.springframework.stereotype.Service;
import ge.bog.bank.backend.model.currency.ExchangedInfo;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public interface ConverterService {

    // default currency GEl
    Optional<ExchangedInfo> convert(String username, String currency, BigDecimal amount);

}
