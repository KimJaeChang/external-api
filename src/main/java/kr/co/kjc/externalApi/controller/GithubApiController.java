package kr.co.kjc.externalApi.controller;

import kr.co.kjc.externalApi.global.gateway.custom.ApiGatewayRouter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/github")
@RequiredArgsConstructor
public class GithubApiController {

  private final ApiGatewayRouter apiGatewayRouter;

//  @Operation(summary = "GitHub API 목록조회", description = "GitHub Secret API 목록을 조회합니다. ")
//  @GetMapping("/{child_external_type}")
//  public Page<ResponseSecretsDto> findAll(
//      @Parameter(in = ParameterIn.PATH, required = true) @PathVariable("child_external_type") EnumChildExternalApiType childExternalApiType) {
//    ResponseDto result = apiGatewayRouter.getGithubApiGateway().getApi(ResponseDto.class);
//    int size = (int) Math.ceil((double) result.getTotal_count() / 10);
//    return PageableExecutionUtils.getPage(result.getSecrets(), Pageable.ofSize(size), result::getTotal_count);
//  }
//
//  @Operation(summary = "GitHub API Name 조회", description = "GitHub Secret Name을 조회합니다. ")
//  @GetMapping("/{child_external_type}/{secret_name}")
//  public ResponseSecretsDto findOneById(
//      @Parameter(in = ParameterIn.PATH, required = true) @PathVariable("child_external_type") EnumChildExternalApiType childExternalApiType,
//      @Parameter(in = ParameterIn.PATH, required = true) @PathVariable("secret_name") String secret_name) {
//    return apiGatewayRouter.getGithubApiGateway()
//        .getApi(GET_PARAMS, secret_name, ResponseSecretsDto.class);
//  }

}
