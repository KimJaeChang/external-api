package kr.co.kjc.externalApi.global.config.client.restclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientGenerator {

  private static final int CONNECTION_TIMEOUT = 60000;
  private static final int READ_TIMEOUT = 60000;

  @Bean
  public RestClient restClient() {
    return RestClient.create();
  }

//  @Bean
//  public RestClient create() {
//
//    RestClient restClient = RestClient.builder().build();
//    RestClientAdapter adapter = restClientAdapter(restClient);
//    HttpServiceProxyFactory factory = httpServiceProxyFactory(adapter);
//
//    return factory.createClient(RestClient.class);
//  }
//
//  @Bean
//  public RestClientAdapter restClientAdapter(RestClient restClient) {
//    return RestClientAdapter.create(restClient);
//  }
//
//  @Bean
//  public HttpServiceProxyFactory httpServiceProxyFactory(RestClientAdapter adapter) {
//    return HttpServiceProxyFactory.builderFor(adapter).build();
//  }
}
