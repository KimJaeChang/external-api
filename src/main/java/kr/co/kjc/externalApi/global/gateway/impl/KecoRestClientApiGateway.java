package kr.co.kjc.externalApi.global.gateway.impl;

import kr.co.kjc.externalApi.global.enums.EnumClientType;
import kr.co.kjc.externalApi.global.gateway.custom.DefaultKecoApiGateway;
import org.springframework.stereotype.Component;

@Component
public class KecoRestClientApiGateway<T> extends DefaultKecoApiGateway<T> {

  public KecoRestClientApiGateway() {
    super(EnumClientType.REST_CLIENT);
  }

}
