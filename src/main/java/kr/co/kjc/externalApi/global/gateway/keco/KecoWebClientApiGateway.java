package kr.co.kjc.externalApi.global.gateway.keco;

import kr.co.kjc.externalApi.global.dtos.api.OpenApiDto;
import kr.co.kjc.externalApi.global.enums.EnumClientRequestType;
import kr.co.kjc.externalApi.global.enums.EnumClientType;

public abstract class KecoWebClientApiGateway<T extends OpenApiDto.KecoApiInterface> extends DefaultKecoApiGateway<T> {

  public KecoWebClientApiGateway() {
    super(EnumClientType.WEB_CLIENT);
  }

  @Override
  public abstract String getHeaderKey(EnumClientType enumClientType, String headerKey);

  @Override
  public abstract <T> T getApi(EnumClientType enumClientType, Class<T> resBody);

  @Override
  public abstract <T> T getApi(EnumClientType enumClientType, EnumClientRequestType enumClientRequestType,
      Class<T> req, Class<T> resBody);

  @Override
  public abstract <T> T postApi(EnumClientType enumClientType, Class<T> reqBody, Class<T> resBody);

  public abstract  <T> T switchGetApi(EnumClientType enumClientType, EnumClientRequestType requestType,
      Class<T> req, Class<T> resBody);

}
