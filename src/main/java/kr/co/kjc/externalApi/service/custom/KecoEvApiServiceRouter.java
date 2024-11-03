package kr.co.kjc.externalApi.service.custom;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType;
import kr.co.kjc.externalApi.global.enums.EnumResponseCode;
import kr.co.kjc.externalApi.global.exception.BaseAPIException;
import kr.co.kjc.externalApi.service.impl.keco.ev.KecoEvApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KecoEvApiServiceRouter {

  private static Map<EnumChildExternalApiType, KecoEvApiService<?>> map = new HashMap<>();

  @PostConstruct
  void init() {
    Arrays.stream(EnumChildExternalApiType.values())
        .forEach(f -> {
          map.put(f, new KecoEvApiService<>(f));
        });
  }

  public KecoEvApiService<?> get(EnumChildExternalApiType enumChildExternalApiType) {
    return map.computeIfAbsent(enumChildExternalApiType, (childExternalApiType) -> {
      throw new BaseAPIException(EnumResponseCode.INVALID_EXTERNAL_CHILD_API);
    });
  }

}
