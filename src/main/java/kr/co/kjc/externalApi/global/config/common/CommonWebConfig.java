package kr.co.kjc.externalApi.global.config.common;

import java.util.List;
import kr.co.kjc.externalApi.global.converter.EnumExternalChildTypeConverter;
import kr.co.kjc.externalApi.global.converter.EnumExternalParentTypeConverter;
import kr.co.kjc.externalApi.global.interceptor.GlobalLoggingInterceptor;
import kr.co.kjc.externalApi.global.resolver.BaseSearchDTOArgumentResolver;
import kr.co.kjc.externalApi.global.resolver.KecoEvRequestDtoArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class CommonWebConfig implements WebMvcConfigurer {

  private final static List<String> LOG_EXCLUDES = List.of("/css/**", "/*.ico", "/error", "/swagger-ui**",
      "/error-page/**");

  private final Environment env;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) { // 기본 resourceHandler 유지하면서 추가
    registry.addResourceHandler("/**")
        .addResourceLocations("classpath:/templates/", "classpath:/static/") // '/'으로 끝나도록
        .setCachePeriod(20);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new GlobalLoggingInterceptor())
        .order(1)
        .addPathPatterns("/**")
        .excludePathPatterns(LOG_EXCLUDES);
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new BaseSearchDTOArgumentResolver());
    resolvers.add(new KecoEvRequestDtoArgumentResolver(env));
  }

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(new EnumExternalParentTypeConverter());
    registry.addConverter(new EnumExternalChildTypeConverter());
  }

  // NOTE : CORS Filter
//  @Bean
//  public FilterRegistrationBean<Filter> corsFilterRegistrationBean() {
//    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//    CorsConfiguration config = new CorsConfiguration();
//    config.addAllowedOrigin("*"); // 허용할 Origin 설정
//    config.addAllowedMethod("*"); // 허용할 HTTP 메서드 설정
//    config.addAllowedHeader("*"); // 허용할 헤더 설정
//    config.addExposedHeader("*");
//    source.registerCorsConfiguration("/**", config);
//    FilterRegistrationBean<Filter> filterBean = new FilterRegistrationBean<>();
//    filterBean.setFilter(new CorsFilter(source));
//    filterBean.setOrder(0);
//    return filterBean;
//  }

}
