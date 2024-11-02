package kr.co.kjc.externalApi.global.gateway.custom;

import static kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType.EV_CHARGERS_INFO;
import static kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType.EV_CHARGERS_STATUS;
import static kr.co.kjc.externalApi.global.enums.EnumParentExternalApiType.KECO;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType;
import kr.co.kjc.externalApi.global.enums.EnumParentExternalApiType;
import kr.co.kjc.externalApi.global.enums.EnumResponseCode;
import kr.co.kjc.externalApi.global.exception.BaseAPIException;
import kr.co.kjc.externalApi.global.gateway.ApiGateway;
import kr.co.kjc.externalApi.global.gateway.keco.KecoApiGatewayRouter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiGatewayRouter {

  private static Map<EnumParentExternalApiType, List<Map<EnumChildExternalApiType, ApiGateway<?>>>> map = new HashMap<>();

  private final KecoApiGatewayRouter kecoApiGatewayRouter;

  @PostConstruct
  public void init() {
    map.put(KECO, List.of(kecoApiMap()));
  }

  Map<EnumChildExternalApiType, ApiGateway<?>> kecoApiMap() {
    ApiGateway<?> evChargerInfoApi = kecoApiGatewayRouter.get(EV_CHARGERS_INFO);
    ApiGateway<?> evChargerStatusApi = kecoApiGatewayRouter.get(EV_CHARGERS_STATUS);

    return Map.of(EV_CHARGERS_INFO, evChargerInfoApi, EV_CHARGERS_STATUS, evChargerStatusApi);
  }

  public ApiGateway<?> get(EnumParentExternalApiType enumParentExternalApiType,
      EnumChildExternalApiType enumChildExternalApiType) {
    return map.computeIfAbsent(enumParentExternalApiType, (parentExternalApiType) -> {
          throw new BaseAPIException(EnumResponseCode.INVALID_EXTERNAL_PARENT_API);
        }).stream()
        .map(m -> m.get(enumChildExternalApiType))
        .findFirst()
        .orElseThrow(() -> new BaseAPIException(EnumResponseCode.INVALID_EXTERNAL_CHILD_API));
  }

}
