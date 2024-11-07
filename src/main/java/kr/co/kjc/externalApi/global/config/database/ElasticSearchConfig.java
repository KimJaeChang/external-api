package kr.co.kjc.externalApi.global.config.database;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy;

@Configuration
@EnableElasticsearchRepositories(basePackages = "kr.co.kjc.externalApi.repository.es", queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND)
@EnableReactiveElasticsearchRepositories(basePackages = "kr.co.kjc.externalApi.repository.es", queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND)
public class ElasticSearchConfig {

}
