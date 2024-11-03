package kr.co.kjc.externalApi.global.gateway.keco;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType;
import kr.co.kjc.externalApi.global.enums.EnumParentExternalApiType;
import kr.co.kjc.externalApi.global.gateway.keco.ev.KecoEvApiGatewayRouter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KecoApiGatewayRouter {

  private static Map<EnumParentExternalApiType, Map<EnumChildExternalApiType, DefaultKecoApiGateway<?>>> map = new HashMap<>();

  private final KecoEvApiGatewayRouter kecoEvApiGatewayRouter;

  @PostConstruct
  void init() {
    Arrays.stream(EnumParentExternalApiType.values())
        .forEach(f -> {
          Arrays.stream(EnumChildExternalApiType.values())
              .filter(ff -> ff.getParentExternalApiType().equals(f))
              .forEach(fff -> {
                map.put(f, kecoEvApiGatewayRouter.findAll());
              });
        });
  }

  public Map<EnumParentExternalApiType, Map<EnumChildExternalApiType, DefaultKecoApiGateway<?>>> findAll() {
    return map;
  }

  public KecoEvApiGatewayRouter getKecoEvApiGatewayRouter() {
    return kecoEvApiGatewayRouter;
  }

}
