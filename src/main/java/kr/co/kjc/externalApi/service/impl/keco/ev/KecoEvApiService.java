package kr.co.kjc.externalApi.service.impl.keco.ev;

import java.util.List;
import java.util.Optional;
import kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType;
import kr.co.kjc.externalApi.service.impl.keco.DefaultKecoApiService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public class KecoEvApiService<T> extends DefaultKecoApiService<T> {

  private final EnumChildExternalApiType childExternalApiType;

  public KecoEvApiService(EnumChildExternalApiType childExternalApiType) {
    this.childExternalApiType = childExternalApiType;
  }

  @Override
  public <T> Page<T> findByExternalType(EnumChildExternalApiType childExternalApiType,
      Class<T> clazz) {
    return new PageImpl<>(List.of());
  }

  @Override
  public <T> Optional<T> findOndByExternalType(EnumChildExternalApiType childExternalApiType,
      Class<T> clazz) {
    return Optional.empty();
  }

}
