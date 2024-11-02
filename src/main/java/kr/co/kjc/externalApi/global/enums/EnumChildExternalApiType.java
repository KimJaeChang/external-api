package kr.co.kjc.externalApi.global.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumChildExternalApiType {

  EV_CHARGERS_INFO("ev-chargers-info", "전기차 충전기 데이터 정보 API", EnumParentExternalApiType.KECO),
  EV_CHARGERS_STATUS("ev-chargers-status", "전기차 충전기 상태 정보 API", EnumParentExternalApiType.KECO),
  ;

  private final String code;
  private final String description;
  private final EnumParentExternalApiType parentExternalApiType;

}
