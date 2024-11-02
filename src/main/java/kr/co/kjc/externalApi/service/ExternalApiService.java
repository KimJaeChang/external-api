package kr.co.kjc.externalApi.service;

import java.util.Optional;
import kr.co.kjc.externalApi.global.dtos.api.open.OpenApiDto;

public interface ExternalApiService {

  default Optional<OpenApiDto.KecoEvChargersInfo.ResponseDto> findByKecoEvChargersInfo(
      OpenApiDto.KecoEvChargersInfo.RequestDto dto) {
    return Optional.empty();
  }

}
