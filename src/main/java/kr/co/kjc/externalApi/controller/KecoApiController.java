package kr.co.kjc.externalApi.controller;

import static kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType.EV_CHARGERS_INFO;

import kr.co.kjc.externalApi.global.dtos.api.OpenApiDto.KecoEvChargersInfo.ResponseDto;
import kr.co.kjc.externalApi.global.gateway.custom.ApiGatewayRouter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/{external_parent_type}")
@RequiredArgsConstructor
public class KecoApiController {

  private final ApiGatewayRouter apiGatewayRouter;


  @GetMapping("/")
  public void create() {
    apiGatewayRouter.getKecoApiGatewayRouter().getKecoEvApiGatewayRouter().get(EV_CHARGERS_INFO).getApi(
        ResponseDto.class);
  }

}
