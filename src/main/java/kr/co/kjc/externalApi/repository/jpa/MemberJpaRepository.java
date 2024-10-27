package kr.co.kjc.externalApi.repository.jpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.kjc.externalApi.domain.jpa.JpaMember;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

public interface MemberJpaRepository extends JpaRepository<JpaMember, Long>,
    QueryByExampleExecutor<JpaMember>, CustomMemberJpaRepository {

}

interface CustomMemberJpaRepository {

}

@Repository
@RequiredArgsConstructor
class CustomMemberJpaRepositoryImpl implements CustomMemberJpaRepository {

  private final JPAQueryFactory queryFactory;

}
