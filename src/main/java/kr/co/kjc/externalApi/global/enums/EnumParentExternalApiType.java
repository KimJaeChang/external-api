package kr.co.kjc.externalApi.global.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumParentExternalApiType {

  KECO("keco", "환경공단 API"),
  ;

  private final String code;
  private final String description;

}
