package kr.co.kjc.externalApi.global.gateway;

import java.util.Optional;
import kr.co.kjc.externalApi.global.enums.EnumClientRequestType;

public abstract class DefaultApiGateway<T, R> implements ApiGateway<T, R> {

  @Override
  public abstract Optional<R> getHeader(R resHeaderKey);

  @Override
  public abstract <T> T getApi(Class<T> resBody);

  @Override
  public abstract <T, R> R getApi(EnumClientRequestType enumClientRequestType, T req, Class<R> resBody);

  @Override
  public abstract <T, R> R postApi(T reqBody, Class<R> resBody);
}
