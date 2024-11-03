package kr.co.kjc.externalApi.global.config.client.webclient;

import lombok.Getter;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

public class CustomBodyInserter<T> implements BodyInserter<T, ReactiveHttpOutputMessage> {

  private final BodyInserter<T, ReactiveHttpOutputMessage> delegate;
  @Getter
  private final T body;

  public CustomBodyInserter(T body) {
    this.delegate = BodyInserters.fromValue(body);
    this.body = body;
  }

  @Override
  public Mono<Void> insert(ReactiveHttpOutputMessage outputMessage, Context context) {
    return this.delegate.insert(outputMessage, context);
  }

  public static <T> CustomBodyInserter<T> of(T body) {
    return new CustomBodyInserter<>(body);
  }
}