package kr.co.kjc.externalApi.global.gateway.keco;

import java.util.Optional;
import kr.co.kjc.externalApi.global.dtos.api.OpenApiDto;
import kr.co.kjc.externalApi.global.enums.EnumClientRequestType;
import org.springframework.stereotype.Component;

@Component
public abstract class KecoRestClientApiGateway<T extends OpenApiDto.KecoApiInterface> extends
    DefaultKecoApiGateway<T> {

  @Override
  public abstract Optional<String> getHeaderKey(String headerKey);

  @Override
  public abstract  <T> T getApi(Class<T> resBody);

  @Override
  public abstract <T> T getApi(EnumClientRequestType enumClientRequestType, Class<T> req, Class<T> resBody);

  @Override
  public abstract <T> T postApi(Class<T> reqBody, Class<T> resBody);
}
