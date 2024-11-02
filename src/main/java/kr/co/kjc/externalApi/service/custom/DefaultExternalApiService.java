package kr.co.kjc.externalApi.service.custom;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType;
import kr.co.kjc.externalApi.global.enums.EnumParentExternalApiType;
import kr.co.kjc.externalApi.service.ExternalApiService;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public abstract class DefaultExternalApiService<T> implements ExternalApiService<T> {

  private final EnumParentExternalApiType parentExternalApiType;
  private final List<EnumChildExternalApiType> childExternalApiTypeList;

  public DefaultExternalApiService(EnumParentExternalApiType parentExternalApiType) {
    this.parentExternalApiType = parentExternalApiType;
    this.childExternalApiTypeList = Arrays.stream(EnumChildExternalApiType.values())
        .filter(f -> f.getParentExternalApiType().equals(parentExternalApiType)).toList();
  }

  @Override
  public abstract Optional<Page<T>> findByExternalType(EnumChildExternalApiType childExternalApiType, Class<T> clazz);

  @Override
  public abstract Optional<T> findOndByExternalType(EnumChildExternalApiType childExternalApiType, Class<T> clazz);

}
