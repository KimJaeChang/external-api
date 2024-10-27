package kr.co.kjc.externalApi.service.custom;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType;
import kr.co.kjc.externalApi.global.enums.EnumParentExternalApiType;
import kr.co.kjc.externalApi.global.enums.EnumResponseCode;
import kr.co.kjc.externalApi.global.exception.BaseAPIException;
import kr.co.kjc.externalApi.service.ExternalApiService;
import kr.co.kjc.externalApi.service.impl.KecoEvApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KecoApiServiceRouter {

  private static Map<EnumParentExternalApiType, Map<EnumChildExternalApiType, ExternalApiService>> parentServiceMap = new HashMap<>();
  private static Map<EnumChildExternalApiType, ExternalApiService> childServiceMap = new HashMap<>();
  private final KecoEvApiService kecoEvApiService;

  @PostConstruct
  public void init() {
    childServiceMap.put(EnumChildExternalApiType.EV_STATIONS_INFO, kecoEvApiService);
    parentServiceMap.put(EnumParentExternalApiType.KECO, childServiceMap);
  }

  public ExternalApiService get(EnumChildExternalApiType enumChildExternalApiType) {
    return parentServiceMap.computeIfAbsent(enumChildExternalApiType.getParentExternalApiType(), (parentExternalApiType) -> {
      throw new BaseAPIException(EnumResponseCode.INVALID_EXTERNAL_PARENT_API);
    }).computeIfAbsent(enumChildExternalApiType, (childExternalApiType) -> {
      throw new BaseAPIException(EnumResponseCode.INVALID_EXTERNAL_CHILD_API);
    });
  }

}
