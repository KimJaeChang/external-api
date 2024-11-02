package kr.co.kjc.externalApi.global.dtos.api.open;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Schema(description = "공공 API Dto",
    name = "OpenApiDto"
)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class OpenApiDto {

  @Schema(description = "환경공단 전기자동차 충전소 정보 Dto",
      name = "OpenApiDto.KecoEvChargersInfo"
  )
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @ToString
  public static class KecoEvChargersInfo {

    @Schema(description = "요청 Dto",
        name = "OpenApiDto.KecoEvChargersInfo.RequestDto"
    )
    @Data
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class RequestDto {

      @Schema(description = "서비스키(공공데이터포털에서 받은 인증키)")
      @NotBlank(message = "ServiceKey 데이터는 필수입니다.")
      private String ServiceKey;

      @Schema(description = "페이지 번호", example = "1")
      @NotBlank(message = "pageNo 데이터는 필수입니다.")
      private Integer pageNo;

      @Schema(description = "한 페이지 결과 수 (최소 10, 최대 9999)", example = "10")
      @NotBlank(message = "numOfRows 데이터는 필수입니다.")
      private Integer numOfRows;

      @Schema(description = "시도 코드 (행정구역코드 앞 2자리)", example = "11")
      private String zcode;
    }

    @Schema(description = "응답 Dto",
        name = "OpenApiDto.KecoEvChargersInfo.ResponseDto"
    )
    @Data
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ResponseDto {

      @Schema(description = "결과코드", example = "00")
      private String resultCode;

      @Schema(description = "결과메시지", example = "OK")
      private String resultMsg;

      @Schema(description = "전체 결과 수", example = "36000")
      private String totalCount;

      @Schema(description = "조회 페이지", example = "1")
      private String pageNo;

      @Schema(description = "	한 페이지 결과 수", example = "1000")
      private String numOfRows;

      @Schema(description = "충전소명", example = "기후대기관")
      private String statNm;

      @Schema(description = "충전소ID", example = "28260005")
      private String statId;

      @Schema(description = "충전기ID", example = "01")
      private String chgerId;

      @Schema(description = "충전기타입", example = "03")
      private String chgerType;

      @Schema(description = "주소", example = "인천광역시 서구 환경로 42")
      private String addr;

      @Schema(description = "위도", example = "37.569620")
      private String lat;

      @Schema(description = "경도", example = "126.641973")
      private String lng;

      @Schema(description = "이용가능시간", example = "24시간 이용가능")
      private String useTime;

      @Schema(description = "기관ID", example = "ME")
      private String busiId;

      @Schema(description = "운영기관명", example = "환경부")
      private String busiNm;

      @Schema(description = "충전기 운영기관 연락처", example = "1661-9408")
      private String busiCall;

      @Schema(description = "충전기상태", example = "2")
      private String stat;

      @Schema(description = "충전기 상태 변경 일시", example = "20190829121020")
      private String statUpdDt;

      @Schema(description = "충전기용량", example = "급속(100kWh동시)")
      private String powerType;

      @Schema(description = "시도 코드(행정구역코드 앞2자리)", example = "28")
      private String zcode;

      @Schema(description = "주차료(Y:무료, N:유료, NULL:현장확인)", example = "Y")
      private String parkingFree;
      
      @Schema(description = "이용제한, 주차요금 등 충전소 안내", example = "공사로 인해 이용 불가")
      private String note;

    }

  }

}
