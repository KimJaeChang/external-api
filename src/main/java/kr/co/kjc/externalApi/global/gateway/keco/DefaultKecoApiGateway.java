package kr.co.kjc.externalApi.global.gateway.keco;

import java.util.Optional;
import kr.co.kjc.externalApi.global.enums.EnumClientRequestType;
import kr.co.kjc.externalApi.global.enums.EnumParentExternalApiType;
import kr.co.kjc.externalApi.global.gateway.DefaultApiGateway;

public abstract class DefaultKecoApiGateway<T, R> extends DefaultApiGateway<T, R> {

  private final EnumParentExternalApiType parentExternalApiType;

  public DefaultKecoApiGateway() {
    this.parentExternalApiType = EnumParentExternalApiType.KECO;
  }

  @Override
  public abstract Optional<R> getHeader(R headerKey);

  @Override
  public abstract <T> T getApi(Class<T> resBody);

  @Override
  public abstract <T, R> R getApi(EnumClientRequestType enumClientRequestType, T req, Class<R> resBody);

  @Override
  public abstract <T, R> R postApi(T reqBody, Class<R> resBody);

}
