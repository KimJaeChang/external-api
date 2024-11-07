package kr.co.kjc.externalApi.global.config.client.custom;

import static kr.co.kjc.externalApi.global.enums.EnumClientType.REST_CLIENT;
import static kr.co.kjc.externalApi.global.enums.EnumClientType.WEB_CLIENT;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import kr.co.kjc.externalApi.global.config.client.ClientGenerator;
import kr.co.kjc.externalApi.global.config.client.restclient.RestClientGenerator;
import kr.co.kjc.externalApi.global.config.client.webclient.WebClientGenerator;
import kr.co.kjc.externalApi.global.enums.EnumClientType;
import kr.co.kjc.externalApi.global.enums.EnumResponseCode;
import kr.co.kjc.externalApi.global.exception.BaseAPIException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientGeneratorRouter {

  private static Map<EnumClientType, ClientGenerator> map = new HashMap<>();

  private final RestClientGenerator restClientGenerator;
  private final WebClientGenerator webClientGenerator;

  @PostConstruct
  void init() {
    map.put(REST_CLIENT, restClientGenerator);
    map.put(WEB_CLIENT, webClientGenerator);
  }

  public ClientGenerator get(EnumClientType enumClientType) {
    return map.computeIfAbsent(enumClientType, (clientType) -> {
      throw new BaseAPIException(EnumResponseCode.INVALID_CLIENT_TYPE);
    });
  }

}
