package kr.co.kjc.externalApi.global.config.client;

import kr.co.kjc.externalApi.global.enums.EnumResponseCode;
import kr.co.kjc.externalApi.global.exception.BaseAPIException;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

public interface ClientGenerator {

  default WebClient webClient() {
    throw new BaseAPIException(EnumResponseCode.SERVICE_UNAVAILABLE_BY_IMPLEMENT);
  }

  default RestClient restClient() {
    throw new BaseAPIException(EnumResponseCode.SERVICE_UNAVAILABLE_BY_IMPLEMENT);
  }

}
