package kr.co.kjc.externalApi.global.converter;

import kr.co.kjc.externalApi.global.enums.EnumParentExternalApiType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EnumExternalParentTypeConverter implements Converter<String, EnumParentExternalApiType> {

  @Override
  public EnumParentExternalApiType convert(String source) {
    return EnumParentExternalApiType.valueOf(source.toUpperCase());
  }

}
