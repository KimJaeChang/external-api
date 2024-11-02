package kr.co.kjc.externalApi.global.enums;

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

}
