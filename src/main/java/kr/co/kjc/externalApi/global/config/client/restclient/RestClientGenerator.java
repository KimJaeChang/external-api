package kr.co.kjc.externalApi.global.config.client.restclient;

import kr.co.kjc.externalApi.global.config.client.DefaultClientGenerator;
import kr.co.kjc.externalApi.global.enums.EnumClientType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Component
public abstract class RestClientGenerator extends DefaultClientGenerator {

  private static final int CONNECTION_TIMEOUT = 60000;
  private static final int READ_TIMEOUT = 60000;

  public RestClientGenerator() {
    super(EnumClientType.REST_CLIENT);
  }

  public static RestClient getBaseUri(final String uri) {
    return RestClient.create(uri);
  }

  public static RestClient create(final String uri) {

    RestClient restClient = RestClient.builder().baseUrl(uri).build();
    RestClientAdapter adapter = RestClientAdapter.create(restClient);
    HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

    return factory.createClient(RestClient.class);
  }
}
