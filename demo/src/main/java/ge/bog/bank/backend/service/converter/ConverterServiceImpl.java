package ge.bog.bank.backend.service.converter;

import ge.bog.bank.backend.model.CurrencyDto;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ConverterServiceImpl implements ConverterService {


    private final WebClient client;

    public ConverterServiceImpl(WebClient client) {
        this.client = client;
    }

    public Optional<BigDecimal> getConvertCurrency(String currency, BigDecimal amount) {
        Mono<CurrencyDto> tmp = client
                .get()
                .uri("%s?amount=%s".formatted(currency, amount.toString()))
                .retrieve()
                .bodyToMono(CurrencyDto.class);
        try {
            CurrencyDto crr = tmp.block();
            if (crr != null) {
                return Optional.ofNullable(crr.getAmount());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    @Bean
    private WebClient.Builder getWebBuilder() {
        return WebClient.builder();
    }

    @Override
    public Optional<BigDecimal> convert(String username, String currency, BigDecimal amount) {

        return getConvertCurrency(currency, amount);
    }


}
