package kr.co.kjc.externalApi.global.gateway.keco.ev;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.util.Optional;
import kr.co.kjc.externalApi.global.config.client.webclient.CustomBodyInserter;
import kr.co.kjc.externalApi.global.config.client.webclient.WebClientGenerator;
import kr.co.kjc.externalApi.global.dtos.api.OpenApiDto;
import kr.co.kjc.externalApi.global.enums.EnumClientRequestType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class KecoEvWebClientApiGateway<T extends OpenApiDto.DefaultKecoApiEvDto> extends
    DefaultKecoEvApiGateway<T> {

  @Value("${service.external.open-api.keco.ev.chargers.host}")
  private String host;
  @Value("${service.external.open-api.keco.ev.chargers.uri}")
  private String uri;

  private final ObjectMapper om;
  private final WebClientGenerator webClientGenerator;

  @Override
  public Optional<String> getHeaderKey(String headerKey) {
    return Optional.empty();
  }

  @Override
  public <T> T getApi(Class<T> resBody) {
    return webClientGenerator.webClient()
        .get()
        .uri(uriBuilder -> uriBuilder.host(host).path(uri).build())
        .retrieve()
        .bodyToMono(resBody)
        .block();
  }

  @Override
  public <T> T getApi(EnumClientRequestType enumClientRequestType,
      Class<T> req, Class<T> resBody) {
    return this.switchGetApi(enumClientRequestType, req, resBody);
  }

  @Override
  public <T> T postApi(Class<T> reqBody, Class<T> resBody) {
    return webClientGenerator.webClient()
        .post()
        .uri(uriBuilder -> uriBuilder.host(host).path(uri).build())
        .body(CustomBodyInserter.of(reqBody))
        .retrieve()
        .bodyToMono(resBody)
        .block();
  }

  private <T> T switchGetApi(EnumClientRequestType enumClientRequestType, Class<T> req,
      Class<T> resBody) {

    UriComponentsBuilder ucb = UriComponentsBuilder.fromHttpUrl(host+uri);
    RequestHeadersUriSpec<?> webclientHEadersUriSpec = webClientGenerator.webClient()
        .get();

    switch (enumClientRequestType) {
      case GET_PARAMS -> {

        MultiValueMap<String, String> queryParams = om.convertValue(req,
            new TypeReference<MultiValueMap<String, String>>() {
            });

        URI ub = ucb.queryParams(queryParams).build().toUri();
        return webclientHEadersUriSpec
            .uri(ub)
            .retrieve()
            .bodyToMono(resBody)
            .block();
      }

      default -> {
        URI ub = ucb.build().toUri();
        return webclientHEadersUriSpec
            .uri(ub)
            .retrieve()
            .bodyToMono(resBody)
            .block();
      }
    }
  }

}
