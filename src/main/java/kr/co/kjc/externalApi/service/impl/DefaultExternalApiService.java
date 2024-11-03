package kr.co.kjc.externalApi.service.impl;

import java.util.Arrays;
import java.util.List;
import kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType;
import kr.co.kjc.externalApi.global.enums.EnumParentExternalApiType;
import kr.co.kjc.externalApi.service.ExternalApiService;
import lombok.Getter;

@Getter
public abstract class DefaultExternalApiService<T> implements ExternalApiService<T> {

  private final EnumParentExternalApiType parentExternalApiType;
  private final List<EnumChildExternalApiType> childExternalApiTypeList;

  public DefaultExternalApiService(EnumParentExternalApiType parentExternalApiType) {
    this.parentExternalApiType = parentExternalApiType;
    this.childExternalApiTypeList = Arrays.stream(EnumChildExternalApiType.values())
        .filter(f -> f.getParentExternalApiType().equals(parentExternalApiType)).toList();
  }

//  @Override
//  public abstract Optional<Page<T>> findByExternalType(EnumChildExternalApiType childExternalApiType, Class<T> clazz);
//
//  @Override
//  public abstract Optional<T> findOndByExternalType(EnumChildExternalApiType childExternalApiType, Class<T> clazz);

}
