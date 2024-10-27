package kr.co.kjc.externalApi.repository.jpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.kjc.externalApi.domain.jpa.JpaCompany;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

public interface CompanyJpaRepository extends JpaRepository<JpaCompany, Long>,
    QueryByExampleExecutor<JpaCompany>, CustomCompanyJpaRepository {

}

interface CustomCompanyJpaRepository {

}

@Repository
@RequiredArgsConstructor
class CustomCompanyJpaRepositoryImpl implements CustomCompanyJpaRepository {

  private final JPAQueryFactory queryFactory;

}
