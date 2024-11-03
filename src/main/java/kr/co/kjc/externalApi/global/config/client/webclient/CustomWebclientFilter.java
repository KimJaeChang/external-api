package kr.co.kjc.externalApi.global.config.client.webclient;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import kr.co.kjc.externalApi.global.exception.BaseAPIException;
import kr.co.kjc.externalApi.global.exception.BaseAPIExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Mono;

@Slf4j
public class CustomWebclientFilter {

  public static ExchangeFilterFunction logRequestFilter() {
    return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
      logRequestLogId(clientRequest);
      logRequestHttpMethod(clientRequest);
      logRequestUrl(clientRequest);
      logRequestHeader(clientRequest);
      return logRequestBody(clientRequest);
    });
  }

  public static ExchangeFilterFunction logResponseFilter() {
    return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
      logResponseStatus(clientResponse);
      logResponseHeader(clientResponse);
      return logResponseBody(clientResponse);
    });
  }

  public static ExchangeFilterFunction onErrorResponseFilter() {
    return ExchangeFilterFunction.ofResponseProcessor(CustomWebclientFilter::onErrorResponseHandler);
  }

  private static void logRequestLogId(ClientRequest clientRequest) {
    log.info("Webclient_Request_LogId : {}", clientRequest.logPrefix());
  }

  private static void logRequestHttpMethod(ClientRequest clientRequest) {
    log.info("Webclient_Request_HttpMethod : [{}]", clientRequest.method());
  }

  private static void logRequestUrl(ClientRequest clientRequest) {
    URI url = clientRequest.url();

    log.info("Webclient_Request_Url : [{}]", url);
  }

  private static void logRequestHeader(ClientRequest clientRequest) {
    Map<String, Object> headers = new HashMap<>();
    clientRequest.headers().forEach((headerName, headerValue) -> {
      headers.put(headerName, clientRequest.headers().get(headerName));
    });

    log.info("Webclient_Request_Header : [{}]", headers);
  }

  private static Mono<ClientRequest> logRequestBody(ClientRequest clientRequest) {

    BodyInserter<?, ? super ClientHttpRequest> bodyInserter = clientRequest.body();
    if (bodyInserter instanceof CustomBodyInserter<?> customBodyInserter) {
      log.info("Webclient_Request_Body : [{}]", customBodyInserter.getBody());

      ClientRequest newClientRequest = ClientRequest.from(clientRequest)
          .body(bodyInserter)
          .build();

      return Mono.just(newClientRequest);
    }

    return Mono.just(clientRequest);
  }

  private static void logResponseStatus(ClientResponse clientResponse) {
    log.info("Webclient_Response_Status : [{}] [{}]", clientResponse.statusCode().value(),
        clientResponse.statusCode());
  }

  private static void logResponseHeader(ClientResponse clientResponse) {
    Map<String, Object> headers = new HashMap<>();
    clientResponse.headers().asHttpHeaders().forEach((headerName, headerValue) -> {
      headers.put(headerName, clientResponse.headers().asHttpHeaders().get(headerName));
    });
    log.info("Webclient_Response_Header : [{}]", headers);
  }

  private static Mono<ClientResponse> logResponseBody(ClientResponse clientResponse) {

    clientResponse.bodyToMono(String.class).flatMap((body) -> {
      byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
      String bodyToString = new String(bytes, StandardCharsets.UTF_8);

      log.info("Webclient_Response_Body : [{}]", bodyToString);

      ClientResponse newClientResponse = ClientResponse.create(clientResponse.statusCode())
          .body(bodyToString)
          .build();

      return Mono.just(newClientResponse);
    });

    return Mono.just(clientResponse);
  }

  private static Mono<ClientResponse> onErrorResponseHandler(ClientResponse clientResponse) {

    if (clientResponse.statusCode().isError()) {
      log.info("Webclient_Error_StatusCode : [{}] [{}]", clientResponse.statusCode().value(),
          HttpStatus.valueOf(clientResponse.statusCode().value()));

      return clientResponse.createException()
          .flatMap(ex -> {
            log.info("Webclient_Error_Exception : [{}]", ex.getMessage());

            return Mono.error(
                new BaseAPIException(
                    BaseAPIExceptionDto.of(HttpStatus.valueOf(ex.getStatusCode().value()),
                        ex.getMessage())));
          });
    }

    return Mono.just(clientResponse); // 오류가 아니면 그대로 반환
  }

}
