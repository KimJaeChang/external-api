package kr.co.kjc.externalApi.global.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumChildExternalApiType {

  EV_STATIONS_INFO("ev-stations-info", "전기차 충전소 데이터 정보 API", EnumParentExternalApiType.KECO),
  EV_STATIONS_STATUS("ev-stations-status", "전기차 충전소 상태 정보 API", EnumParentExternalApiType.KECO),
  ;

  private final String code;
  private final String description;
  private final EnumParentExternalApiType parentExternalApiType;

}
