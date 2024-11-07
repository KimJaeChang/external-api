package kr.co.kjc.externalApi.service.impl;

import kr.co.kjc.externalApi.global.gateway.custom.ApiGatewayRouter;
import kr.co.kjc.externalApi.service.ExternalApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GithubApiService<T> implements ExternalApiService<T> {

  private final ApiGatewayRouter apiGatewayRouter;





}
