package kr.co.kjc.externalApi.global.gateway.custom;

import kr.co.kjc.externalApi.global.config.client.generator.WebClientGenerator;
import kr.co.kjc.externalApi.global.gateway.ApiGateway;
import org.springframework.stereotype.Component;

@Component
public class DefaultKecoApiGateway<T> extends WebClientGenerator<T> implements ApiGateway<T> {

}
