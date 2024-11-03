package kr.co.kjc.externalApi.service.custom;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType;
import kr.co.kjc.externalApi.service.impl.keco.DefaultKecoApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KecoApiServiceRouter {

  private static Map<EnumChildExternalApiType, DefaultKecoApiService<?>> map = new HashMap<>();

  private final KecoEvApiServiceRouter kecoEvApiServiceRouter;

  @PostConstruct
  void init() {
//    Arrays.stream(EnumChildExternalApiType.values())
//        .forEach(f -> {
//          map.put(f, kecoEvApiServiceRouter.get(f));
//        });
  }

  public KecoEvApiServiceRouter getKecoEvApiServiceRouter() {
    return kecoEvApiServiceRouter;
  }

}
