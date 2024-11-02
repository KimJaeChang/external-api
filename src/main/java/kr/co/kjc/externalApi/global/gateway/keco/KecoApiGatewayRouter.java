package kr.co.kjc.externalApi.global.gateway.keco;

import static kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType.EV_CHARGERS_INFO;
import static kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType.EV_CHARGERS_STATUS;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType;
import kr.co.kjc.externalApi.global.enums.EnumResponseCode;
import kr.co.kjc.externalApi.global.exception.BaseAPIException;
import kr.co.kjc.externalApi.global.gateway.ApiGateway;
import kr.co.kjc.externalApi.global.gateway.keco.ev.KecoEvApiGatewayRouter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KecoApiGatewayRouter {

  private static Map<EnumChildExternalApiType, ApiGateway<?>> map = new HashMap<>();

  private final KecoEvApiGatewayRouter kecoEvApiGatewayRouter;

  @PostConstruct
  void init() {
    evApiInit();
  }

  void evApiInit() {
    map.put(EV_CHARGERS_INFO, kecoEvApiGatewayRouter.get(EV_CHARGERS_INFO));
    map.put(EV_CHARGERS_STATUS, kecoEvApiGatewayRouter.get(EV_CHARGERS_STATUS));
  }

  public ApiGateway<?> get(EnumChildExternalApiType enumChildExternalApiType) {
    return map.computeIfAbsent(enumChildExternalApiType, (childExternalApiType) -> {
      throw new BaseAPIException(EnumResponseCode.INVALID_CHILD_EXTERNAL_API_TYPE);
    });
  }

}
