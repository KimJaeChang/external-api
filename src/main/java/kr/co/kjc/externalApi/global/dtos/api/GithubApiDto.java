package kr.co.kjc.externalApi.global.dtos.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class GithubApiDto {

  @Schema(description = "GithubApi Secrets",
      name = "GithubApiDto.SecretsApiDto"
  )
  public static class SecretsApiDto {

    @Schema(description = "GithubApi Secrets ResponseDto",
        name = "GithubApiDto.SecretsApiDto.ResponseDto"
    )
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    public static class ResponseDto {

      @Schema(description = "데이터 총 Count")
      private Long total_count;

      @Schema(description = "secrets 데이터")
      private List<ResponseSecretsDto> secrets;

      @Schema(description = "GithubApi Secrets ResponseSecretsDto",
          name = "GithubApiDto.SecretsApiDto.ResponseDto.ResponseSecretsDto"
      )
      @Getter
      @NoArgsConstructor(access = AccessLevel.PRIVATE)
      @AllArgsConstructor(access = AccessLevel.PRIVATE)
      @ToString
      public static class ResponseSecretsDto {
        @Schema(description = "secrets 이름")
        private String name;

        @Schema(description = "등록일자")
        @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "Asia/Seoul")
        private LocalDateTime created_at;

        @Schema(description = "수정일자")
        @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "Asia/Seoul")
        private LocalDateTime updated_at;
      }

    }

  }

}
