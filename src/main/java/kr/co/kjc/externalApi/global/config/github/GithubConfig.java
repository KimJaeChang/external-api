package kr.co.kjc.externalApi.global.config.github;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GithubConfig {

  @Bean
  public GithubProperty githubProperty() {
    return new GithubProperty("");
  }

}
