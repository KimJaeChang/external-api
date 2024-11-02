package kr.co.kjc.externalApi.global.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumClientRequestType {

  GET("get", "GET API"),
  GET_PARAMS("get-params", "GET API"),
  ;

  private final String code;
  private final String description;

}
