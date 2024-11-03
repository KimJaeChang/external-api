package kr.co.kjc.externalApi.service.impl.keco;

import java.util.Optional;
import kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType;
import kr.co.kjc.externalApi.global.enums.EnumParentExternalApiType;
import kr.co.kjc.externalApi.service.impl.DefaultExternalApiService;
import org.springframework.data.domain.Page;

public abstract class DefaultKecoApiService<T> extends DefaultExternalApiService<T> {

  protected DefaultKecoApiService() {
    super(EnumParentExternalApiType.KECO);
  }

  public abstract <T> Page<T> findByExternalType(
      EnumChildExternalApiType childExternalApiType, Class<T> clazz);

  public abstract <T> Optional<T> findOndByExternalType(EnumChildExternalApiType childExternalApiType,
      Class<T> clazz);

}
