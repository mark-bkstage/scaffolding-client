package com.pst.crewpro.config;

import com.pst.crewpro.client.ProfilesClient;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.netty.http.client.HttpClient;

@Configuration
@EnableRetry
public class ProfilesClientConfig {

  @Value("${profiles.host:http://localhost:8080}")
  String profilesHost;

  @Value("${profiles.timeout:30}")
  int profilesTimeout;

  WebClient profilesWebClient() {
    return WebClient.builder()
        .clientConnector(
            new ReactorClientHttpConnector(
                HttpClient.create()
                    .option(
                        ChannelOption.CONNECT_TIMEOUT_MILLIS,
                        ((Long) TimeUnit.SECONDS.toMillis(profilesTimeout)).intValue())
                    .responseTimeout(Duration.ofSeconds(profilesTimeout))
                    .doOnConnected(
                        connection ->
                            connection
                                .addHandlerLast(new ReadTimeoutHandler(profilesTimeout))
                                .addHandlerLast(new WriteTimeoutHandler(profilesTimeout)))))
        .exchangeStrategies(
            ExchangeStrategies.builder()
                .codecs(c -> c.defaultCodecs().enableLoggingRequestDetails(true))
                .build())
        .baseUrl(profilesHost)
        .build();
  }

  @SneakyThrows
  @Bean
  ProfilesClient profilesClient() {
    HttpServiceProxyFactory httpServiceProxyFactory =
        HttpServiceProxyFactory.builder(WebClientAdapter.forClient(profilesWebClient()))
            .blockTimeout(Duration.ofSeconds(profilesTimeout))
            .build();
    return httpServiceProxyFactory.createClient(ProfilesClient.class);
  }
}
