package kr.co.kjc.externalApi.global.gateway.custom;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType;
import kr.co.kjc.externalApi.global.enums.EnumParentExternalApiType;
import kr.co.kjc.externalApi.global.enums.EnumResponseCode;
import kr.co.kjc.externalApi.global.exception.BaseAPIException;
import kr.co.kjc.externalApi.global.gateway.ApiGateway;
import kr.co.kjc.externalApi.global.gateway.impl.KecoApiGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiGatewayRouter {

  private static Map<EnumParentExternalApiType, Map<EnumChildExternalApiType, ApiGateway<?>>> parentApiMap = new HashMap<>();
  private static Map<EnumChildExternalApiType, ApiGateway<?>> childApiMap = new HashMap<>();
  private final KecoApiGateway kecoApiGateway;

  @PostConstruct
  public void init() {
    initKecoEvApiService();
  }

  private void initKecoEvApiService() {
    chlidInit();
    parentInit();
  }

  private void parentInit() {
    parentApiMap.put(EnumParentExternalApiType.KECO, childApiMap);
  }

  private void chlidInit() {
    childApiMap.put(EnumChildExternalApiType.EV_CHARGERS_INFO, kecoApiGateway);
  }

  public ApiGateway<?> get(EnumParentExternalApiType enumParentExternalApiType, EnumChildExternalApiType enumChildExternalApiType) {
    return parentApiMap.computeIfAbsent(enumParentExternalApiType, (parentExternalApiType) -> {
      throw new BaseAPIException(EnumResponseCode.INVALID_EXTERNAL_PARENT_API);
    }).computeIfAbsent(enumChildExternalApiType, (childExternalApiType) -> {
      throw new BaseAPIException(EnumResponseCode.INVALID_EXTERNAL_CHILD_API);
    });
  }

}
