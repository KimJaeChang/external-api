package kr.co.kjc.externalApi.global.gateway.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import kr.co.kjc.externalApi.global.dtos.api.open.OpenApiDto;
import kr.co.kjc.externalApi.global.enums.EnumClientRequestType;
import kr.co.kjc.externalApi.global.enums.EnumClientType;
import kr.co.kjc.externalApi.global.exception.BaseAPIException;
import kr.co.kjc.externalApi.global.exception.BaseAPIExceptionDto;
import kr.co.kjc.externalApi.global.gateway.custom.DefaultKecoApiGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class KecoApiGateway<T extends OpenApiDto> extends DefaultKecoApiGateway<T> {

  private final String host;
  private final String uri;
  private final ObjectMapper om;

  public KecoApiGateway(@Value("${service.external.open-api.keco.ev.chargers.host}") String host,
      @Value("${service.external.open-api.keco.ev.chargers.uri}") String uri,
      ObjectMapper om) {
    this.host = host;
    this.uri = uri;
    this.om = om;
  }

  @Override
  public String getHeaderKey(EnumClientType enumClientType, String headerKey) {
    return super.getApi(host, uri, headerKey);
  }

  @Override
  public <T> T getApi(EnumClientType enumClientType, Class<T> resBody) {
    return super.getApi(host, uri, resBody);
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

  @Override
  public <T> T switchGetApi(EnumClientType enumClientType, EnumClientRequestType requestType,
      Class<T> req, Class<T> resBody) {
    switch (enumClientType) {
      case WEB_CLIENT -> {
        return this.switchWebClientGetApi(requestType, req, resBody);
      }
      case REST_CLIENT -> {
        return this.switchRestClientGetApi(requestType, req, resBody);
      }
      default -> {
        throw new BaseAPIException(
            BaseAPIExceptionDto.of(HttpStatus.BAD_REQUEST, "clientType을 지정해주세요."));
      }
    }
  }

  @Override
  public <T> T switchWebClientGetApi(EnumClientRequestType enumClientRequestType, Class<T> req,
      Class<T> resBody) {

    UriComponentsBuilder ucb = UriComponentsBuilder.fromUriString(uri);

    switch (enumClientRequestType) {
      case GET_PARAMS -> {

        MultiValueMap<String, String> queryParams = om.convertValue(req,
            new TypeReference<MultiValueMap<String, String>>() {
            });

        URI ub = ucb.queryParams(queryParams).build().toUri();
        return super.getApi(host, ub, resBody);
      }
      default -> {
        URI ub = ucb.build().toUri();
        return super.getApi(host, ub, resBody);
      }
    }
  }

}
