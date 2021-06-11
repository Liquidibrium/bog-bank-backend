package ge.bog.bank.backend.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
public class WebConfig {
    private static final int TIME_TO_WAIT_IN_MILLISECONDS = 5000;
    //    @Value("${my.currency.converter.url}")
    private static final String CONVERTER_URL = "http://localhost:8881/api/convert/";

    @Bean
    public static WebClient getWebClient() {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIME_TO_WAIT_IN_MILLISECONDS)
                .responseTimeout(Duration.ofMillis(TIME_TO_WAIT_IN_MILLISECONDS))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(TIME_TO_WAIT_IN_MILLISECONDS, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(TIME_TO_WAIT_IN_MILLISECONDS, TimeUnit.MILLISECONDS)));
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(CONVERTER_URL)
                .build();
    }
}
