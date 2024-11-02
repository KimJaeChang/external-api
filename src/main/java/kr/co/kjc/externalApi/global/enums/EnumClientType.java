package kr.co.kjc.externalApi.global.enums;

import java.util.Arrays;
import kr.co.kjc.externalApi.global.exception.BaseAPIException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumClientType {

  REST_CLIENT("rest-client", "rest-client"),
  WEB_CLIENT("web-client", "web-client"),
  ;

  private final String code;
  private final String description;

  public static EnumClientType fromCode(String code) {
    return Arrays.stream(EnumClientType.values())
        .filter(f -> f.getCode().equals(code))
        .findFirst()
        .orElseThrow(() -> new BaseAPIException(EnumResponseCode.INVALID_CLIENT_TYPE));
  }

}
