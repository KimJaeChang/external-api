package kr.co.kjc.externalApi.global.gateway;

import kr.co.kjc.externalApi.global.enums.EnumClientRequestType;
import kr.co.kjc.externalApi.global.enums.EnumClientType;
import kr.co.kjc.externalApi.global.enums.EnumResponseCode;
import kr.co.kjc.externalApi.global.exception.BaseAPIException;

public interface ApiGateway<T> {

  default String getHeaderKey(EnumClientType enumClientType, String headerKey) {
    throw new BaseAPIException(EnumResponseCode.SERVICE_UNAVAILABLE_BY_IMPLEMENT);
  }

  default <T> T getApi(EnumClientType enumClientType, Class<T> resBody) {
    throw new BaseAPIException(EnumResponseCode.SERVICE_UNAVAILABLE_BY_IMPLEMENT);
  }

  default <T> T getApi(EnumClientType enumClientType, EnumClientRequestType enumClientRequestType,
      Class<T> req, Class<T> resBody) {
    throw new BaseAPIException(EnumResponseCode.SERVICE_UNAVAILABLE_BY_IMPLEMENT);
  }

  default <T> T postApi(EnumClientType enumClientType, Class<T> reqBody, Class<T> resBody) {
    throw new BaseAPIException(EnumResponseCode.SERVICE_UNAVAILABLE_BY_IMPLEMENT);
  }
}
