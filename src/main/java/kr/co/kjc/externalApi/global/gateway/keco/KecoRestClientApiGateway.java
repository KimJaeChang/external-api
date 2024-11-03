package kr.co.kjc.externalApi.global.gateway.keco;

import java.util.Optional;
import kr.co.kjc.externalApi.global.enums.EnumClientRequestType;
import org.springframework.stereotype.Component;

@Component
public abstract class KecoRestClientApiGateway<T, R> extends DefaultKecoApiGateway<T, R> {

  @Override
  public abstract Optional<String> getHeaderKey(String headerKey);

  @Override
  public abstract  <T> T getApi(Class<T> resBody);

  @Override
  public abstract <T, R> R getApi(EnumClientRequestType enumClientRequestType, T req, Class<R> resBody);

  @Override
  public abstract <T> T postApi(Class<T> reqBody, Class<T> resBody);
}
