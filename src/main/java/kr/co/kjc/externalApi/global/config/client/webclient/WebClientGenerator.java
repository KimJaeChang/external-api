package kr.co.kjc.externalApi.global.config.client.webclient;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.net.URI;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLException;
import kr.co.kjc.externalApi.global.config.client.DefaultClientGenerator;
import kr.co.kjc.externalApi.global.enums.EnumClientType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Component
public abstract class WebClientGenerator<T> extends DefaultClientGenerator {

  private static final int CONNECTION_TIMEOUT = 60000;
  private static final int READ_TIMEOUT = 60000;
  private static final int WRITE_TIMEOUT = 60000;

  public WebClientGenerator() {
    super(EnumClientType.WEB_CLIENT);
  }

  public static WebClient create(final String uri) {

    ConnectionProvider provider = ConnectionProvider.builder("external-api")
        .maxIdleTime(Duration.ofSeconds(3))
        .maxLifeTime(Duration.ofSeconds(3))
        .build();

    HttpClient httpClient = HttpClient.create(provider)
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECTION_TIMEOUT)
        .secure(t -> {
          try {
            SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();
          } catch (SSLException e) {
            throw new RuntimeException(e);
          }
        })
        .doOnConnected(conn ->
            conn.addHandlerLast(new ReadTimeoutHandler(READ_TIMEOUT, TimeUnit.MILLISECONDS))
                .addHandlerLast(new WriteTimeoutHandler(WRITE_TIMEOUT, TimeUnit.MILLISECONDS))
        );

    return WebClient.builder()
        .baseUrl(uri)
        .clientConnector(new ReactorClientHttpConnector(httpClient))
        .filter(CustomWebclientFilter.logRequestFilter())
        .filter(CustomWebclientFilter.onErrorResponseFilter())
        .filter(CustomWebclientFilter.logResponseFilter())
        .build();
  }

  private String getApi(String host, String uri, String headerKey) {
    StringBuilder sb = new StringBuilder();

    Optional<ResponseEntity<Void>> opHeaders = this.create(host).get()
            .uri(uriBuilder -> uriBuilder.path(uri).build())
            .retrieve()
//        .onStatus(HttpStatusCode::isError, clientResponse -> {
//          return clientResponse.bodyToMono(String.class).flatMap(body -> {
//            return Mono.error(
//                new BaseAPIException(BaseAPIExceptionDto.of(HttpStatus.valueOf(clientResponse.statusCode().value()), body))
//            );
//          });
//        })
            .toBodilessEntity()
            .blockOptional();

    if(opHeaders.isPresent()) {
      HttpHeaders headers = opHeaders.get().getHeaders();
      if(headers.containsKey(headerKey)) {
        sb.append(headers.get(headerKey));
      }
    }

    return sb.toString();
  }

  private <T> T getApi(String host, String uri, Class<T> resBody) {
    return this.create(host).get()
        .uri(uriBuilder -> uriBuilder.path(uri).build())
        .retrieve()
//        .onStatus(HttpStatusCode::isError, clientResponse -> {
//          return clientResponse.bodyToMono(String.class).flatMap(body -> {
//            return Mono.error(
//                new BaseAPIException(BaseAPIExceptionDto.of(HttpStatus.valueOf(clientResponse.statusCode().value()), body))
//            );
//          });
//        })
        .bodyToMono(resBody)
//        .onErrorResume(throwable -> {
//          switch (throwable) {
//            case BaseAPIException ex -> throw new BaseAPIException(BaseAPIExceptionDto.of(ex.getDto().getStatus(), ex.getMessage()));
//            case WebClientRequestException ex -> throw new BaseAPIException(BaseAPIExceptionDto.of(HttpStatus.BAD_REQUEST, ex.getMessage()));
//            case WebClientResponseException ex -> throw new BaseAPIException(BaseAPIExceptionDto.of(HttpStatus.valueOf(ex.getStatusCode().value()), ex.getMessage()));
//            default -> throw new BaseAPIException(BaseAPIExceptionDto.of(HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage()));
//          }
//        })
        .block();
  }

  public <T> T getApi(String host, URI ub, Class<T> resBody) {
    return this.create(host).get()
        .uri(ub)
        .retrieve()
//        .onStatus(HttpStatusCode::isError, clientResponse -> {
//          return clientResponse.bodyToMono(String.class).flatMap(body -> {
//            return Mono.error(
//                new BaseAPIException(BaseAPIExceptionDto.of(HttpStatus.valueOf(clientResponse.statusCode().value()), body))
//            );
//          });
//        })
        .bodyToMono(resBody)
//        .onErrorResume(throwable -> {
//          switch (throwable) {
//            case BaseAPIException ex -> throw new BaseAPIException(BaseAPIExceptionDto.of(ex.getDto().getStatus(), ex.getMessage()));
//            case WebClientRequestException ex -> throw new BaseAPIException(BaseAPIExceptionDto.of(HttpStatus.BAD_REQUEST, ex.getMessage()));
//            case WebClientResponseException ex -> throw new BaseAPIException(BaseAPIExceptionDto.of(HttpStatus.valueOf(ex.getStatusCode().value()), ex.getMessage()));
//            default -> throw new BaseAPIException(BaseAPIExceptionDto.of(HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage()));
//          }
//        })
        .block();
  }
}
