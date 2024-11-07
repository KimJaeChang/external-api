package kr.co.kjc.externalApi.global.enums;

import java.util.Arrays;
import kr.co.kjc.externalApi.global.exception.BaseAPIException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumChildExternalApiType {

  EV_CHARGERS_INFO("ev-chargers-info", "전기차 충전기 데이터 정보 API", EnumParentExternalApiType.KECO),
  EV_CHARGERS_STATUS("ev-chargers-status", "전기차 충전기 상태 정보 API", EnumParentExternalApiType.KECO),
  GITHUB_SECRETS("github-secrets", "GitHub Secrets API", EnumParentExternalApiType.GITHUB),
  ;

  private final String code;
  private final String description;
  private final EnumParentExternalApiType parentExternalApiType;

  public static EnumChildExternalApiType fromCode(String code) {
    return Arrays.stream(EnumChildExternalApiType.values())
        .filter(f -> f.getCode().equals(code))
        .findFirst()
        .orElseThrow(() -> new BaseAPIException(EnumResponseCode.INVALID_CHILD_EXTERNAL_API_TYPE));
  }

}
