package kr.co.kjc.externalApi.repository.redis;

import kr.co.kjc.externalApi.domain.redis.RedisToken;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface TokenRedisRepository extends CrudRepository<RedisToken, String> {

}

interface CustomTokenRedisRepository {

}

@Repository
@RequiredArgsConstructor
class CustomTokenRedisRepositoryImpl implements CustomTokenRedisRepository {

  private final RedisTemplate<String, Object> redisTemplate;

}

