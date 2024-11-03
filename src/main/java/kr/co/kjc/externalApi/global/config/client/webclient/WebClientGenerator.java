package kr.co.kjc.externalApi.global.config.client.webclient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Configuration
public class WebClientGenerator {

  private static final int CONNECTION_TIMEOUT = 60000;
  private static final int READ_TIMEOUT = 60000;
  private static final int WRITE_TIMEOUT = 60000;

  @Bean
  public WebClient webClient() {

    ConnectionProvider provider = ConnectionProvider.builder("external-api")
        .maxIdleTime(Duration.ofSeconds(3))
        .maxLifeTime(Duration.ofSeconds(3))
        .build();

    HttpClient httpClient = HttpClient.create(provider)
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECTION_TIMEOUT)
//        .secure(t -> {
//          try {
//            SslContextBuilder
//                .forClient()
//                .trustManager(InsecureTrustManagerFactory.INSTANCE)
//                .build();
//          } catch (SSLException e) {
//            throw new RuntimeException(e);
//          }
//        })
        .doOnConnected(conn ->
            conn.addHandlerLast(new ReadTimeoutHandler(READ_TIMEOUT, TimeUnit.MILLISECONDS))
                .addHandlerLast(new WriteTimeoutHandler(WRITE_TIMEOUT, TimeUnit.MILLISECONDS))
        );

    return WebClient.builder()
        .clientConnector(new ReactorClientHttpConnector(httpClient))
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .filter(CustomWebclientFilter.logRequestFilter())
        .filter(CustomWebclientFilter.onErrorResponseFilter())
        .filter(CustomWebclientFilter.logResponseFilter())
        .build();
  }

}
