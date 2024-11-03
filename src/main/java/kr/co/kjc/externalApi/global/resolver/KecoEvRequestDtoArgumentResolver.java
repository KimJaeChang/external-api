package kr.co.kjc.externalApi.global.resolver;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;
import kr.co.kjc.externalApi.global.dtos.api.OpenApiDto.KecoEvChargersInfo.RequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class KecoEvRequestDtoArgumentResolver implements HandlerMethodArgumentResolver  {

  @Value("${service.external.open-api.keco.ev.chargers.service-key}")
  private String ServiceKey;

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.getGenericParameterType().equals(RequestDto.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

    HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
    Integer pageNo = Objects.nonNull(request.getParameter("pageNo")) ? Integer.valueOf(request.getParameter("pageNo")) : 1;
    Integer numOfRows = Objects.nonNull(request.getParameter("numOfRows")) ? Integer.valueOf(request.getParameter("pageNo")) : 10;
    String zcode = Optional.ofNullable(request.getParameter("zcode")).orElse(null);

    return RequestDto.of(ServiceKey, pageNo, numOfRows, zcode);
  }
}
