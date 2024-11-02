package kr.co.kjc.externalApi.global.config.client.custom;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import kr.co.kjc.externalApi.global.enums.EnumClientType;
import kr.co.kjc.externalApi.global.enums.EnumResponseCode;
import kr.co.kjc.externalApi.global.exception.BaseAPIException;
import kr.co.kjc.externalApi.global.gateway.keco.DefaultKecoApiGateway;
import kr.co.kjc.externalApi.global.gateway.keco.KecoRestClientApiGateway;
import kr.co.kjc.externalApi.global.gateway.keco.KecoWebClientApiGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiClientRouter {

  private static Map<EnumClientType, DefaultKecoApiGateway<?>> map = new HashMap<>();

  private final KecoWebClientApiGateway<?> kecoWebClientApiGateway;
  private final KecoRestClientApiGateway<?> kecoRestClientApiGateway;

  @PostConstruct
  void init() {
    map.put(EnumClientType.WEB_CLIENT, kecoWebClientApiGateway);
    map.put(EnumClientType.REST_CLIENT, kecoRestClientApiGateway);
  }

  public DefaultKecoApiGateway<?> get(EnumClientType enumClientType) {
    return map.computeIfAbsent(enumClientType, (clientType) -> {
      throw new BaseAPIException(EnumResponseCode.INVALID_CLIENT_TYPE);
    });
  }
}