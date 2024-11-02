package kr.co.kjc.externalApi.global.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseAPIExceptionDto {

  private HttpStatus status;

  private String detail;

  @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Asia/Seoul")
  private LocalDateTime timestamp;

  @Builder
  public BaseAPIExceptionDto(HttpStatus status, String detail) {
    this.status = status;
    this.detail = detail;
    this.timestamp = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
  }

  public static BaseAPIExceptionDto of(HttpStatus status, String detail) {
    return BaseAPIExceptionDto.builder()
        .status(status)
        .detail(detail)
        .build();
  }

}
