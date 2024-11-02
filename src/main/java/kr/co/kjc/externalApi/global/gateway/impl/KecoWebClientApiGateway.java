package kr.co.kjc.externalApi.global.gateway.impl;

import kr.co.kjc.externalApi.global.enums.EnumClientType;
import kr.co.kjc.externalApi.global.gateway.custom.DefaultKecoApiGateway;
import org.springframework.stereotype.Component;

@Component
public class KecoWebClientApiGateway<T> extends DefaultKecoApiGateway<T> {

  public KecoWebClientApiGateway() {
    super(EnumClientType.WEB_CLIENT);
  }

  protected String getHost() {
    return super.getHost();
  }

  protected String getUri() {
    return super.getUri();
  }
}
