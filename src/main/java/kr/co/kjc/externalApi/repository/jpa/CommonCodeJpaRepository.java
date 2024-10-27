package kr.co.kjc.externalApi.repository.jpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.kjc.externalApi.domain.jpa.CommonCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

public interface CommonCodeJpaRepository extends JpaRepository<CommonCode, Long>,
    QueryByExampleExecutor<CommonCode>, CustomCommonCodeJpaRepository {

}

interface CustomCommonCodeJpaRepository {

}

@Repository
@RequiredArgsConstructor
class CustomCommonCodeJpaRepositoryImpl implements CustomCommonCodeJpaRepository {

  private final JPAQueryFactory queryFactory;

}