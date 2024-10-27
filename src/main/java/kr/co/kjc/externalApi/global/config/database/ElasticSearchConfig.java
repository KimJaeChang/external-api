package kr.co.kjc.externalApi.global.config.database;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "kr.co.kjc.externalApi.repository.es")
public class ElasticSearchConfig {

}
