package kr.co.kjc.externalApi.domain.redis;

import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * timeToLive는 기본적으로 Second 이다. 60L = 1분 redis와 연동중 서버를 shutdown 시키면 timeToLive를 체크를 못한다. 기동시에 초기화를
 * 할 수 없을까?
 */
@Getter
@NoArgsConstructor
@RedisHash(value = "Token", timeToLive = 60L * 60L)
public class RedisToken {

  @Id
  private String refreshToken;

  @Embedded
  private RedisTokenBody redisTokenBody;

  public static RedisToken of(String refreshToken, RedisTokenBody redisTokenBody) {
    RedisToken result = new RedisToken();
    result.refreshToken = refreshToken;
    result.redisTokenBody = redisTokenBody;
    return result;
  }

  public static RedisToken createTokenByRefreshToken(String refreshToken) {
    RedisToken result = new RedisToken();
    result.refreshToken = refreshToken;
    return result;
  }
}
