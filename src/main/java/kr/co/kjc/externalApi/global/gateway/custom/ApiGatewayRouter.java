package kr.co.kjc.externalApi.global.gateway.custom;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType;
import kr.co.kjc.externalApi.global.enums.EnumClientType;
import kr.co.kjc.externalApi.global.enums.EnumParentExternalApiType;
import kr.co.kjc.externalApi.global.enums.EnumResponseCode;
import kr.co.kjc.externalApi.global.exception.BaseAPIException;
import kr.co.kjc.externalApi.global.gateway.ApiGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiGatewayRouter {

  @Value("${service.external.open-api.keco.ev.chargers.client-type}")
  private String defaultEvClientType;

  private static Map<EnumParentExternalApiType, Map<EnumChildExternalApiType, ApiGateway<?>>> parentApiMap = new HashMap<>();
  private static Map<EnumChildExternalApiType, ApiGateway<?>> childApiMap = new HashMap<>();
  private static Map<EnumClientType, DefaultKecoApiGateway<?>> clientTypeMap = new HashMap<>();

  private final KecoApiGatewayRouter kecoApiGatewayRouter;

  @PostConstruct
  public void init() {
    initKecoEvApiService();
  }

  private void initKecoEvApiService() {
    childInit();
    parentInit();
  }

  private void parentInit() {
    parentApiMap.put(EnumParentExternalApiType.KECO, childApiMap);
  }

  private void childInit() {
    childApiMap.put(EnumChildExternalApiType.EV_CHARGERS_INFO, evClientTypeInit());
    childApiMap.put(EnumChildExternalApiType.EV_CHARGERS_STATUS, evClientTypeInit());
  }

  private DefaultKecoApiGateway<?> evClientTypeInit() {
    EnumClientType evClientType = EnumClientType.fromCode(defaultEvClientType);
    return kecoApiGatewayRouter.get(evClientType);
  }

  public ApiGateway<?> get(EnumParentExternalApiType enumParentExternalApiType, EnumChildExternalApiType enumChildExternalApiType) {
    return parentApiMap.computeIfAbsent(enumParentExternalApiType, (parentExternalApiType) -> {
      throw new BaseAPIException(EnumResponseCode.INVALID_EXTERNAL_PARENT_API);
    }).computeIfAbsent(enumChildExternalApiType, (childExternalApiType) -> {
      throw new BaseAPIException(EnumResponseCode.INVALID_EXTERNAL_CHILD_API);
    });
  }

}
