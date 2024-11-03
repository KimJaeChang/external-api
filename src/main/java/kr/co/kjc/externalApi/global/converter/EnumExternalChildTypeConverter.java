package kr.co.kjc.externalApi.global.converter;

import kr.co.kjc.externalApi.global.enums.EnumChildExternalApiType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EnumExternalChildTypeConverter implements Converter<String, EnumChildExternalApiType> {

  @Override
  public EnumChildExternalApiType convert(String source) {
    return EnumChildExternalApiType.fromCode(source);
  }

}
