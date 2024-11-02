package kr.co.kjc.externalApi.global.gateway.keco.ev;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import kr.co.kjc.externalApi.global.config.client.custom.ApiClientRouter;
import kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType;
import kr.co.kjc.externalApi.global.enums.EnumClientType;
import kr.co.kjc.externalApi.global.enums.EnumResponseCode;
import kr.co.kjc.externalApi.global.exception.BaseAPIException;
import kr.co.kjc.externalApi.global.gateway.keco.DefaultKecoApiGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KecoEvApiGatewayRouter {

  private static Map<EnumChildExternalApiType, DefaultKecoApiGateway<?>> map = new HashMap<>();

  @Value("${service.external.open-api.keco.ev.chargers.api-client}")
  private String defaultEvApiClient;

  private final ApiClientRouter apiClientRouter;

  @PostConstruct
  void init() {
    EnumClientType evClientType = EnumClientType.fromCode(defaultEvApiClient);
    map.put(EnumChildExternalApiType.EV_CHARGERS_STATUS, apiClientRouter.get(evClientType));
    map.put(EnumChildExternalApiType.EV_CHARGERS_INFO, apiClientRouter.get(evClientType));
  }

  public DefaultKecoApiGateway<?> get(EnumChildExternalApiType enumChildExternalApiType) {
    return map.computeIfAbsent(enumChildExternalApiType, (childExternalApiType) -> {
      throw new BaseAPIException(EnumResponseCode.INVALID_CHILD_EXTERNAL_API_TYPE);
    });
  }
}
