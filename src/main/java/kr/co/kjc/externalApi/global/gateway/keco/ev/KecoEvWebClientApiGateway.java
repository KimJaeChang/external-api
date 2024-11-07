package kr.co.kjc.externalApi.global.gateway.keco.ev;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import kr.co.kjc.externalApi.global.config.client.webclient.CustomBodyInserter;
import kr.co.kjc.externalApi.global.config.client.webclient.WebClientGenerator;
import kr.co.kjc.externalApi.global.enums.EnumClientRequestType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.codec.xml.Jaxb2XmlDecoder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class KecoEvWebClientApiGateway<T, R> extends DefaultKecoEvApiGateway<T, R> {

  @Value("${service.external.open-api.keco.ev.chargers.host}")
  private String host;
  @Value("${service.external.open-api.keco.ev.chargers.info.uri}")
  private String uri;

  private final ObjectMapper om;
  private final WebClientGenerator webClientGenerator;

  @Override
  public Optional<R> getHeader(R headerKey) {
    return Optional.empty();
  }

  @Override
  public <T> T getApi(Class<T> resBody) {

    WebClient mutateWebclient = webClientGenerator.webClient().mutate()
        .codecs(clientCodecConfigurer -> {
          clientCodecConfigurer.defaultCodecs().jackson2JsonDecoder(new Jaxb2XmlDecoder());
        })
        .build();

    return mutateWebclient
        .get()
        .uri(uriBuilder -> uriBuilder.host(host).path(uri).build())
        .retrieve()
        .bodyToMono(resBody)
        .block();
  }

  @Override
  public <T, R> R getApi(EnumClientRequestType enumClientRequestType, T req, Class<R> resBody) {
    return this.switchGetApi(enumClientRequestType, req, resBody);
  }

  @Override
  public <T, R> R postApi(T reqBody, Class<R> resBody) {

    WebClient mutateWebclient = webClientGenerator.webClient().mutate()
        .codecs(clientCodecConfigurer -> {
          clientCodecConfigurer.defaultCodecs().jackson2JsonDecoder(new Jaxb2XmlDecoder());
        })
        .build();

    return mutateWebclient
        .post()
        .uri(uriBuilder -> uriBuilder.host(host).path(uri).build())
        .body(CustomBodyInserter.of(reqBody))
        .retrieve()
        .bodyToMono(resBody)
        .block();
  }

  private <T, R> R switchGetApi(EnumClientRequestType enumClientRequestType, T req,
      Class<R> resBody) {

    UriComponentsBuilder ucb = UriComponentsBuilder.fromHttpUrl(host+uri);
    WebClient mutateWebclient = webClientGenerator.webClient().mutate()
        .codecs(clientCodecConfigurer -> {
          clientCodecConfigurer.defaultCodecs().jackson2JsonDecoder(new Jaxb2XmlDecoder(new MimeType(
              MimeTypeUtils.TEXT_XML, StandardCharsets.UTF_8)));
        })
        .build();

    switch (enumClientRequestType) {
      case GET_PARAMS -> {

        Map<String, Object> queryParams = om.convertValue(req,
            new TypeReference<HashMap<String, Object>>() {
            });

        URI ub = ucb
            .queryParam("serviceKey", String.valueOf(queryParams.get("serviceKey")))
            .queryParam("pageNo", String.valueOf(queryParams.get("pageNo")))
            .queryParam("numOfRows", String.valueOf(queryParams.get("numOfRows")))
            .queryParam("zcode", String.valueOf(queryParams.get("zcode")))
            .build().toUri();

//        MultiValueMap<String, String> queryParams = om.convertValue(req,
//            new TypeReference<MultiValueMap<String, String>>() {
//            });
//
//        URI ub = ucb.queryParams(queryParams)
//            .build().toUri();

        return mutateWebclient
            .get()
            .uri(ub)
            .retrieve()
            .bodyToMono(resBody)
            .block();
      }

      default -> {
        URI ub = ucb.build().toUri();
        return mutateWebclient
            .get()
            .uri(ub)
            .retrieve()
            .bodyToMono(resBody)
            .block();
      }
    }
  }

}
