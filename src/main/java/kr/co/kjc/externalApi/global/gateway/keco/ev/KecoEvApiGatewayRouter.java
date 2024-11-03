package kr.co.kjc.externalApi.global.gateway.keco.ev;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType;
import kr.co.kjc.externalApi.global.enums.EnumClientType;
import kr.co.kjc.externalApi.global.enums.EnumResponseCode;
import kr.co.kjc.externalApi.global.exception.BaseAPIException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KecoEvApiGatewayRouter {

  @Value("${service.external.open-api.keco.ev.chargers.api-client}")
  private String defaultApiClient;

  private static Map<EnumChildExternalApiType, DefaultKecoEvApiGateway<?, ?>> map = new HashMap<>();

  private final KecoEvRestClientApiGateway<?, ?> kecoEvRestClientApiGateway;
  private final KecoEvWebClientApiGateway<?, ?> kecoEvWebClientApiGateway;

  @PostConstruct
  void init() {
    EnumClientType clientType = EnumClientType.fromCode(defaultApiClient);

    Arrays.stream(EnumChildExternalApiType.values())
        .forEach(childExternalApiType -> {
          switch (childExternalApiType) {
            default -> {
              switch (clientType) {
                case REST_CLIENT -> map.put(childExternalApiType, kecoEvRestClientApiGateway);
                case WEB_CLIENT -> map.put(childExternalApiType, kecoEvWebClientApiGateway);
              }
            }
          }
        });
  }

  public Map<EnumChildExternalApiType, DefaultKecoEvApiGateway<?, ?>> findAll() {
    return map;
  }

  public DefaultKecoEvApiGateway<?, ?> get(EnumChildExternalApiType enumChildExternalApiType) {
    return map.computeIfAbsent(enumChildExternalApiType, (childExternalApiType) -> {
      throw new BaseAPIException(EnumResponseCode.INVALID_CHILD_EXTERNAL_API_TYPE);
    });
  }
}
