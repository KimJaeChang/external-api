package kr.co.kjc.externalApi.global.gateway;

import java.util.Optional;
import kr.co.kjc.externalApi.global.enums.EnumClientRequestType;
import kr.co.kjc.externalApi.global.enums.EnumResponseCode;
import kr.co.kjc.externalApi.global.exception.BaseAPIException;

public interface ApiGateway<T, R> {

  default Optional<String> getHeaderKey(String headerKey) {
    throw new BaseAPIException(EnumResponseCode.SERVICE_UNAVAILABLE_BY_IMPLEMENT);
  }

  default <T> T getApi(Class<T> resBody) {
    throw new BaseAPIException(EnumResponseCode.SERVICE_UNAVAILABLE_BY_IMPLEMENT);
  }

  default <T, R> R getApi(EnumClientRequestType enumClientRequestType, T req, Class<R> resBody) {
    throw new BaseAPIException(EnumResponseCode.SERVICE_UNAVAILABLE_BY_IMPLEMENT);
  }

  default <T> T postApi(Class<T> reqBody, Class<T> resBody) {
    throw new BaseAPIException(EnumResponseCode.SERVICE_UNAVAILABLE_BY_IMPLEMENT);
  }
}
