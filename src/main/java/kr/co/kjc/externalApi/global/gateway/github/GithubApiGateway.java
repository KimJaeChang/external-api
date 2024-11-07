package kr.co.kjc.externalApi.global.gateway.github;

import static kr.co.kjc.externalApi.global.enums.EnumClientType.WEB_CLIENT;

import kr.co.kjc.externalApi.global.config.client.custom.ClientGeneratorRouter;
import kr.co.kjc.externalApi.global.enums.EnumClientRequestType;
import kr.co.kjc.externalApi.global.gateway.ApiGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class GithubApiGateway<T, R> implements ApiGateway<T, R> {

  @Value("${service.external.github.host}")
  private String host;

  @Value("${service.external.github.secret-api.uri}")
  private String secretApiUri;

  @Value("${service.external.github.secret-api.headers.authorization}")
  private String githubToken;

  private final ClientGeneratorRouter clientGeneratorRouter;

  @Override
  public <T> T getApi(Class<T> resBody) {
    return clientGeneratorRouter.get(WEB_CLIENT).webClient()
        .get()
        .uri(uriBuilder -> UriComponentsBuilder.fromHttpUrl(host+secretApiUri).build().toUri())
        .headers(httpHeaders -> {
          httpHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer " + githubToken);
          httpHeaders.set(HttpHeaders.ACCEPT, "application/vnd.github.v3+json");
          httpHeaders.set("X-GitHub-Api-Version", "2022-11-28");
        })
        .retrieve()
        .bodyToMono(resBody)
        .block();
  }

  @Override
  public <T, R> R getApi(EnumClientRequestType enumClientRequestType, T req, Class<R> resBody) {
    return clientGeneratorRouter.get(WEB_CLIENT).webClient()
        .get()
        .uri(uriBuilder -> UriComponentsBuilder.fromHttpUrl(host+secretApiUri).path("/"+(req)).build().toUri())
        .headers(httpHeaders -> {
          httpHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer " + githubToken);
          httpHeaders.set(HttpHeaders.ACCEPT, "application/vnd.github.v3+json");
          httpHeaders.set("X-GitHub-Api-Version", "2022-11-28");
        })
        .retrieve()
        .bodyToMono(resBody)
        .block();
  }

}
