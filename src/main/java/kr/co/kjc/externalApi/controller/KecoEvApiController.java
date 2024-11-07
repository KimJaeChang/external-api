package kr.co.kjc.externalApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import kr.co.kjc.externalApi.global.annotation.KecoEvChargersRequest;
import kr.co.kjc.externalApi.global.dtos.api.OpenApiDto.KecoEvChargersInfo.RequestDto;
import kr.co.kjc.externalApi.global.dtos.api.OpenApiDto.KecoEvChargersInfo.ResponseDto;
import kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType;
import kr.co.kjc.externalApi.global.gateway.custom.ApiGatewayRouter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/keco")
@RequiredArgsConstructor
public class KecoEvApiController {

  private final ApiGatewayRouter apiGatewayRouter;

  @Operation(summary = "환경공단 전기자동차 충전기 API", description = "환경공단 전기자동차 충전기 API를 조회합니다. ")
  @GetMapping("/{child_external_type}")
  public Page<ResponseDto> findAll(
      @Parameter(in = ParameterIn.PATH, required = true) @PathVariable("child_external_type") EnumChildExternalApiType childExternalApiType,
      @KecoEvChargersRequest RequestDto dto) {

    System.out.println("\t");
    System.out.println("dto : " + dto);
    System.out.println("\t");

//    ResponseBodyDto result = apiGatewayRouter.getKecoApiGatewayRouter()
//        .getKecoEvApiGatewayRouter()
//        .get(childExternalApiType).getApi(GET_PARAMS, dto, ResponseBodyDto.class);

    String result = apiGatewayRouter.getKecoApiGatewayRouter()
        .getKecoEvApiGatewayRouter()
//        .get(childExternalApiType).getApi(GET_PARAMS, dto, String.class);
        .get(childExternalApiType).postApi(dto, String.class);

//    return new PageImpl<>(List.of(result));
    return null;
  }

}
