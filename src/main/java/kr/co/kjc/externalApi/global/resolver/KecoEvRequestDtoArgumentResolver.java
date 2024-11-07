package kr.co.kjc.externalApi.global.resolver;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Objects;
import kr.co.kjc.externalApi.global.annotation.KecoEvChargersRequest;
import kr.co.kjc.externalApi.global.dtos.api.OpenApiDto.KecoEvChargersInfo.RequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class KecoEvRequestDtoArgumentResolver implements HandlerMethodArgumentResolver  {

  private final Environment env;

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasParameterAnnotation(KecoEvChargersRequest.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

    HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
    String serviceKey = env.getProperty("OPEN_API_SECRET_KEY_KECO_EV_CHARGERS");
    Integer pageNo = Objects.nonNull(request.getParameter("pageNo")) ? Integer.valueOf(request.getParameter("pageNo")) : 1;
    Integer numOfRows = Objects.nonNull(request.getParameter("numOfRows")) ? Integer.valueOf(request.getParameter("numOfRows")) : 10;
    Integer zcode = Objects.nonNull(request.getParameter("zcode")) ? Integer.valueOf(request.getParameter("zcode")) : null;
//    String pageNo = Objects.nonNull(request.getParameter("pageNo")) ? request.getParameter("pageNo") : "1";
//    String numOfRows = Objects.nonNull(request.getParameter("numOfRows")) ? request.getParameter("numOfRows") : "10";
//    String zcode = Optional.ofNullable(request.getParameter("zcode")).orElse(null);

    return RequestDto.of(serviceKey, pageNo, numOfRows, zcode);
  }
}
