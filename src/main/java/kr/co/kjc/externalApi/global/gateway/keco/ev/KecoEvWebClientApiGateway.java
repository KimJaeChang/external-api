package kr.co.kjc.externalApi.global.gateway.keco.ev;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import kr.co.kjc.externalApi.global.dtos.api.OpenApiDto;
import kr.co.kjc.externalApi.global.enums.EnumClientRequestType;
import kr.co.kjc.externalApi.global.enums.EnumClientType;
import kr.co.kjc.externalApi.global.gateway.keco.KecoWebClientApiGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class KecoEvWebClientApiGateway<T extends OpenApiDto.DefaultKecoApiEvDto> extends
    KecoWebClientApiGateway<T> {

  @Value("${service.external.open-api.keco.ev.chargers.host}")
  private String host;
  @Value("${service.external.open-api.keco.ev.chargers.uri}")
  private String uri;

  private final ObjectMapper om;

  @Override
  public String getHeaderKey(EnumClientType enumClientType, String headerKey) {
    return null;
  }

  @Override
  public <T> T getApi(EnumClientType enumClientType, Class<T> resBody) {
    return null;
  }

  @Override
  public <T> T getApi(EnumClientType enumClientType, EnumClientRequestType enumClientRequestType,
      Class<T> req, Class<T> resBody) {
    return this.switchGetApi(enumClientType, enumClientRequestType, req, resBody);
  }

  @Override
  public <T> T postApi(EnumClientType enumClientType, Class<T> reqBody, Class<T> resBody) {
    return null;
  }

  public <T> T switchGetApi(EnumClientType enumClientType, EnumClientRequestType requestType,
      Class<T> req, Class<T> resBody) {
    return this.switchWebClientGetApi(requestType, req, resBody);
  }

  private <T> T switchWebClientGetApi(EnumClientRequestType enumClientRequestType, Class<T> req,
      Class<T> resBody) {

    UriComponentsBuilder ucb = UriComponentsBuilder.fromUriString(uri);

    switch (enumClientRequestType) {
      case GET_PARAMS -> {

        MultiValueMap<String, String> queryParams = om.convertValue(req,
            new TypeReference<MultiValueMap<String, String>>() {
            });

        URI ub = ucb.queryParams(queryParams).build().toUri();
        return null;
      }
      default -> {
        URI ub = ucb.build().toUri();
        return null;
      }
    }
  }

}
