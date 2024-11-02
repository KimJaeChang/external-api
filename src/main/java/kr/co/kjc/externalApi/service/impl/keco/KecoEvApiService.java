package kr.co.kjc.externalApi.service.impl.keco;

import java.util.Optional;
import kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType;
import kr.co.kjc.externalApi.global.gateway.custom.ApiGatewayRouter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class KecoEvApiService<T> extends DefaultKecoApiService<T> {

  private final ApiGatewayRouter apiGatewayRouter;

  public KecoEvApiService(ApiGatewayRouter apiGatewayRouter) {
    this.apiGatewayRouter = apiGatewayRouter;
  }

  @Override
  public Optional<Page<T>> findByExternalType(EnumChildExternalApiType childExternalApiType,
      Class<T> clazz) {
    return Optional.empty();
  }

  @Override
  public Optional<T> findOndByExternalType(EnumChildExternalApiType childExternalApiType,
      Class<T> clazz) {
    return Optional.empty();
  }

}
