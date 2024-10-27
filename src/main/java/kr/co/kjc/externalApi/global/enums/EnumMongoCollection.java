package kr.co.kjc.externalApi.global.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumMongoCollection {

  SETTLEMENT_LOG("settlement-log", "결제/정산 Collection", EnumMongoDataBase.COMMON_LOG),
  ;

  private final String code;
  private final String description;
  private final EnumMongoDataBase mongoDataBase;

}
