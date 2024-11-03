package kr.co.kjc.externalApi.service;

import java.util.Optional;
import kr.co.kjc.externalApi.global.dtos.api.OpenApiDto;
import kr.co.kjc.externalApi.global.dtos.api.OpenApiDto.KecoEvChargersInfo.RequestDto;
import kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType;
import org.springframework.data.domain.Page;

public interface ExternalApiService<T> {

  default <T> Page<T> findByExternalType(EnumChildExternalApiType childExternalApiType, RequestDto clazz) {
    return Page.empty();
  }

  default <T> Optional<T> findOndByExternalType(EnumChildExternalApiType childExternalApiType, Class<T> clazz) {
    return Optional.empty();
  }

  default Optional<OpenApiDto.KecoEvChargersInfo.ResponseDto> findByKecoEvChargersInfo(
      OpenApiDto.KecoEvChargersInfo.RequestDto dto) {
    return Optional.empty();
  }

}
