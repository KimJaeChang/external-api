package kr.co.kjc.externalApi.service.custom;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType;
import kr.co.kjc.externalApi.global.enums.EnumParentExternalApiType;
import kr.co.kjc.externalApi.service.ExternalApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExternalApiServiceRouter {

  private static Map<EnumParentExternalApiType, Map<EnumChildExternalApiType, ExternalApiService<?>>> map = new HashMap<>();

  private final KecoApiServiceRouter kecoApiServiceRouter;

  @PostConstruct
  public void init() {
  }

  public KecoApiServiceRouter getKecoServiceRouter() {
    return kecoApiServiceRouter;
  }

}
