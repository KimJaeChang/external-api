package kr.co.kjc.externalApi.global.gateway.custom;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType;
import kr.co.kjc.externalApi.global.gateway.ApiGateway;
import kr.co.kjc.externalApi.global.gateway.github.GithubApiGateway;
import kr.co.kjc.externalApi.global.gateway.keco.KecoApiGatewayRouter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiGatewayRouter {

  private static Map<EnumChildExternalApiType, List<ApiGateway<?, ?>>> map = new HashMap<>();

  private final GithubApiGateway<?, ?> githubApiGateway;
  private final KecoApiGatewayRouter kecoApiGatewayRouter;

  @PostConstruct
  public void init() {
  }

  public GithubApiGateway<?, ?> getGithubApiGateway() {
    return githubApiGateway;
  }

  public KecoApiGatewayRouter getKecoApiGatewayRouter() {
    return kecoApiGatewayRouter;
  }

}
