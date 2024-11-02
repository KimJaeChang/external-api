package kr.co.kjc.externalApi.service;

import java.util.Optional;
import kr.co.kjc.externalApi.global.dtos.api.OpenApiDto;
import kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType;
import org.springframework.data.domain.Page;

public interface ExternalApiService<T> {

  Optional<Page<T>> findByExternalType(EnumChildExternalApiType childExternalApiType, Class<T> clazz);

  Optional<T> findOndByExternalType(EnumChildExternalApiType childExternalApiType, Class<T> clazz);

  default Optional<OpenApiDto.KecoEvChargersInfo.ResponseDto> findByKecoEvChargersInfo(
      OpenApiDto.KecoEvChargersInfo.RequestDto dto) {
    return Optional.empty();
  }

}
