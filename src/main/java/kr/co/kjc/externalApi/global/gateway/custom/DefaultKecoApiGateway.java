package kr.co.kjc.externalApi.global.gateway.custom;

import kr.co.kjc.externalApi.global.config.client.generator.WebClientGenerator;
import kr.co.kjc.externalApi.global.enums.EnumClientType;
import kr.co.kjc.externalApi.global.gateway.ApiGateway;
import org.springframework.beans.factory.annotation.Value;

public abstract class DefaultKecoApiGateway<T> extends WebClientGenerator<T> implements ApiGateway<T> {

  private final EnumClientType enumClientType;

  @Value("${service.external.open-api.keco.ev.chargers.host}")
  private String host;

  @Value("${service.external.open-api.keco.ev.chargers.uri}")
  private String uri;

  public DefaultKecoApiGateway(EnumClientType enumClientType) {
    this.enumClientType = enumClientType;
  }

  protected String getHost() {
    return this.host;
  }

  protected String getUri() {
    return this.uri;
  }
}
