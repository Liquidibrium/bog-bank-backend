package ge.bog.bank.backend.service.converter;

import ge.bog.bank.backend.model.currency.ExchangedInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ConverterServiceImpl implements ConverterService {

    // TODO  add converted money to user's account


    private final WebClient client;
    private static final String converterAPIFormat = "%s?amount=%s";

    public ConverterServiceImpl(WebClient client) {
        this.client = client;
    }

    public Optional<ExchangedInfo> getConvertCurrency(String currency, BigDecimal amount) {
        Mono<ExchangedInfo> exchanged = client
                .get()
                .uri(converterAPIFormat.formatted(currency, amount.toString()))
                .retrieve()
                .bodyToMono(ExchangedInfo.class);
        try {
            ExchangedInfo exc = exchanged.block(); // async
            return Optional.ofNullable(exc);
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
    public Optional<ExchangedInfo> convert(String username, String currency, BigDecimal amount) {
        // TODO validate user
        return getConvertCurrency(currency.toUpperCase(), amount);
    }


}
