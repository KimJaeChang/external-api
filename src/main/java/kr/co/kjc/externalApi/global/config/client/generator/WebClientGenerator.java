package kr.co.kjc.externalApi.global.config.client.generator;

import java.net.URI;
import java.util.Optional;
import kr.co.kjc.externalApi.global.config.client.webclient.WebClientConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public abstract class WebClientGenerator<T> {

  public String getApi(String host, String uri, String headerKey) {
    StringBuilder sb = new StringBuilder();

    Optional<ResponseEntity<Void>> opHeaders = WebClientConfig.getBaseUri(host).get()
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

  public <T> T getApi(String host, String uri, Class<T> resBody) {
    return WebClientConfig.getBaseUri(host).get()
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
    return WebClientConfig.getBaseUri(host).get()
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
