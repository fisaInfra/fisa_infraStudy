package com.fisa.infra.account.repository.querydsl;

import com.fisa.infra.account.domain.QAccount;
import com.fisa.infra.account.domain.dto.AccountDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class QueryAccountRepositoryImpl implements QueryAccountRepository{
    private final JPAQueryFactory queryFactory;

    public Optional<AccountDTO> queryFindAccountByLoginId(String id) {

        QAccount account = QAccount.account;

        JPAQuery<AccountDTO> jpaQuery = queryFactory
                .select(Projections.constructor(AccountDTO.class,
                        account.accountId,
                        account.loginId,
                        account.pwd,
                        account.imageUrl,
                        account.name,
                        account.belong,
                        account.gender,
                        account.stack,
                        account.portfolio,
                        account.job,
                        account.isDeleted
                ))
                .from(account)
                .where(account.loginId.eq(id));

        // JPQL 쿼리를 문자열로 얻어와서 로깅
        log.info("JPQL Query: {}", jpaQuery.toString());
        return Optional.ofNullable(jpaQuery.fetchOne());
    }

}
