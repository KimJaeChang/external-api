package kr.co.kjc.externalApi.global.gateway.custom;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType;
import kr.co.kjc.externalApi.global.gateway.DefaultApiGateway;
import kr.co.kjc.externalApi.global.gateway.keco.KecoApiGatewayRouter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiGatewayRouter {

  private static Map<EnumChildExternalApiType, List<DefaultApiGateway<?>>> map = new HashMap<>();

  private final KecoApiGatewayRouter kecoApiGatewayRouter;

  @PostConstruct
  public void init() {
  }

  public KecoApiGatewayRouter getKecoApiGatewayRouter() {
    return kecoApiGatewayRouter;
  }

}
