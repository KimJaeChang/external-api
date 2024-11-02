package kr.co.kjc.externalApi.global.gateway.keco;

import kr.co.kjc.externalApi.global.dtos.api.OpenApiDto;
import kr.co.kjc.externalApi.global.enums.EnumClientRequestType;
import kr.co.kjc.externalApi.global.enums.EnumClientType;
import kr.co.kjc.externalApi.global.enums.EnumParentExternalApiType;
import kr.co.kjc.externalApi.global.gateway.ApiGateway;

public abstract class DefaultKecoApiGateway<T extends OpenApiDto.KecoApiDto> implements ApiGateway<T> {

  private final EnumParentExternalApiType parentExternalApiType;
  private final EnumClientType clientType;

  public DefaultKecoApiGateway(EnumClientType enumClientType) {
    this.parentExternalApiType = EnumParentExternalApiType.KECO;
    this.clientType = enumClientType;
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

}
