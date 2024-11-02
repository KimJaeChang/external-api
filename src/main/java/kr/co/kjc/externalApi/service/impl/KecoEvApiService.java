package kr.co.kjc.externalApi.service.impl;

import java.util.Optional;
import kr.co.kjc.externalApi.global.dtos.api.open.OpenApiDto.KecoEvChargersInfo.RequestDto;
import kr.co.kjc.externalApi.global.dtos.api.open.OpenApiDto.KecoEvChargersInfo.ResponseDto;
import kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType;
import kr.co.kjc.externalApi.global.enums.EnumClientType;
import kr.co.kjc.externalApi.global.enums.EnumParentExternalApiType;
import kr.co.kjc.externalApi.global.gateway.custom.ApiGatewayRouter;
import kr.co.kjc.externalApi.service.ExternalApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KecoEvApiService implements ExternalApiService {

  private final ApiGatewayRouter apiGatewayRouter;

  @Override
  public Optional<ResponseDto> findByKecoEvChargersInfo(RequestDto dto) {
//    ResponseDto api = apiGatewayRouter.get(EnumParentE/

    return ExternalApiService.super.findByKecoEvChargersInfo(dto);
  }
}
