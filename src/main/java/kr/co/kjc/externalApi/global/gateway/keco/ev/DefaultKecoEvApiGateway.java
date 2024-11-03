package kr.co.kjc.externalApi.global.gateway.keco.ev;

import java.util.Optional;
import kr.co.kjc.externalApi.global.enums.EnumClientRequestType;
import kr.co.kjc.externalApi.global.gateway.keco.DefaultKecoApiGateway;

public abstract class DefaultKecoEvApiGateway<T> extends DefaultKecoApiGateway<T> {

  @Override
  public abstract Optional<String> getHeaderKey(String headerKey);

  @Override
  public abstract  <T> T getApi(Class<T> resBody);

  @Override
  public abstract  <T> T getApi(EnumClientRequestType enumClientRequestType, Class<T> req, Class<T> resBody);

  @Override
  public abstract <T> T postApi(Class<T> reqBody, Class<T> resBody);
}
