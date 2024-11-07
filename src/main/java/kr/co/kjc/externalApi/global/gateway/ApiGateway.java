package kr.co.kjc.externalApi.global.gateway;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import kr.co.kjc.externalApi.global.enums.EnumClientRequestType;
import kr.co.kjc.externalApi.global.enums.EnumResponseCode;
import kr.co.kjc.externalApi.global.exception.BaseAPIException;

public interface ApiGateway<T, R> {

  /**
   * @param resHeaderKey
   * @implSpec 파라미터에 맞는 헤더 조회
   * @return Optional<R>
   */
  default Optional<R> getHeader(R resHeaderKey) {
    throw new BaseAPIException(EnumResponseCode.SERVICE_UNAVAILABLE_BY_IMPLEMENT);
  }

  /**
   * @param reqHeaderKey  파라미터로 전송하는 헤더
   * @param resHeaderKey  응답으로 받는 헤더
   * @implSpec 파라미터에 맞는 헤더 조회
   * @return Optional<R>
   */
  default Optional<R> getHeader(T reqHeaderKey, R resHeaderKey) {
    throw new BaseAPIException(EnumResponseCode.SERVICE_UNAVAILABLE_BY_IMPLEMENT);
  }

  /**
   * @param reqHeaderKey  파라미터로 전송하는 헤더
   * @param resHeaderKey  응답으로 받는 헤더들
   * @implSpec 파라미터에 맞는 헤더들 조회
   * @return List<Map<String, Object>>
   */
  default List<Map<String, Object>> getHeaders(T reqHeaderKey, List<Map<String, Object>> resHeaderKey) {
    throw new BaseAPIException(EnumResponseCode.SERVICE_UNAVAILABLE_BY_IMPLEMENT);
  }

  /**
   * @param reqHeaders  파라미터로 전송하는 헤더들
   * @implSpec 파라미터에 맞는 헤더들 조회
   * @return List<Map<String, Object>>
   */
  default List<Map<String, Object>> getHeaders(List<Map<String, Object>> reqHeaders) {
    throw new BaseAPIException(EnumResponseCode.SERVICE_UNAVAILABLE_BY_IMPLEMENT);
  }

  /**
   * @param <T> resBody
   * @implSpec  [GET] 파라미터 없는채로 조회
   * @return T - resBody
   */
  default <T> T getApi(Class<T> resBody) {
    throw new BaseAPIException(EnumResponseCode.SERVICE_UNAVAILABLE_BY_IMPLEMENT);
  }

  /**
   * @param <T> req
   * @implSpec  [GET] 파라미터 있는채로 조회
   * @return R - resBody
   */
  default <T, R> R getApi(EnumClientRequestType enumClientRequestType, T req, Class<R> resBody) {
    throw new BaseAPIException(EnumResponseCode.SERVICE_UNAVAILABLE_BY_IMPLEMENT);
  }

  /**
   *
   * @param <T>  reqBody
   * @implSpec  [POST] Body 있는채로 전송
   * @return R - resBody
   */
  default <T, R> R postApi(T reqBody, Class<R> resBody) {
    throw new BaseAPIException(EnumResponseCode.SERVICE_UNAVAILABLE_BY_IMPLEMENT);
  }
}
