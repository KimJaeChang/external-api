package kr.co.kjc.externalApi.global.config.client;

import kr.co.kjc.externalApi.global.enums.EnumClientType;

public abstract class DefaultClientGenerator {

  private final EnumClientType enumClientType;

  public DefaultClientGenerator(EnumClientType enumClientType) {
    this.enumClientType = enumClientType;
  }

}
