package kr.co.kjc.externalApi.global.gateway.keco;

import kr.co.kjc.externalApi.global.dtos.api.OpenApiDto;
import kr.co.kjc.externalApi.global.enums.EnumClientRequestType;
import kr.co.kjc.externalApi.global.enums.EnumClientType;

public abstract class KecoRestClientApiGateway<T extends OpenApiDto.KecoApiDto> extends DefaultKecoApiGateway<T> {

  public KecoRestClientApiGateway() {
    super(EnumClientType.REST_CLIENT);
  }

  @Override
  public abstract String getHeaderKey(EnumClientType enumClientType, String headerKey);

  @Override
  public abstract  <T> T getApi(EnumClientType enumClientType, Class<T> resBody);

  @Override
  public abstract <T> T getApi(EnumClientType enumClientType, EnumClientRequestType enumClientRequestType,
      Class<T> req, Class<T> resBody);

  @Override
  public abstract <T> T postApi(EnumClientType enumClientType, Class<T> reqBody, Class<T> resBody);
}
