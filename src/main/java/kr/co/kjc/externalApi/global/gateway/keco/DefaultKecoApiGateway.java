package kr.co.kjc.externalApi.global.gateway.keco;

import java.util.Optional;
import kr.co.kjc.externalApi.global.enums.EnumClientRequestType;
import kr.co.kjc.externalApi.global.enums.EnumParentExternalApiType;
import kr.co.kjc.externalApi.global.gateway.DefaultApiGateway;

public abstract class DefaultKecoApiGateway<T> extends DefaultApiGateway<T> {

  private final EnumParentExternalApiType parentExternalApiType;

  public DefaultKecoApiGateway() {
    this.parentExternalApiType = EnumParentExternalApiType.KECO;
  }

  @Override
  public abstract Optional<String> getHeaderKey(String headerKey);

  @Override
  public abstract <T> T getApi(Class<T> resBody);

  @Override
  public abstract <T> T getApi(EnumClientRequestType enumClientRequestType, Class<T> req, Class<T> resBody);

  @Override
  public abstract <T> T postApi(Class<T> reqBody, Class<T> resBody);

}
