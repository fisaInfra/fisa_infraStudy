package com.fisa.infra.account.repository.querydsl;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.account.domain.dto.AccountDTO;
import com.fisa.infra.account.dummy.DummyAccount;
import com.fisa.infra.config.QuerydslTestConfig;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


import java.util.Optional;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;


@AutoConfigureTestDatabase(replace = NONE)
@Import(QuerydslTestConfig.class)
@DataJpaTest
class QueryAccountRepositoryImplTest {

    @Autowired
    private EntityManager em;


    @Autowired
    private QueryAccountRepository queryAccountRepository;

    private Account account;

    @BeforeEach
    void setUp() {
        account = DummyAccount.dummy();

    }

    @Test
    void queryFindAccountByLoginId() {
        //given
        em.persist(account);

        //when
        Optional<AccountDTO> op = queryAccountRepository.queryFindAccountByLoginId("test");

        Assertions.assertThat(op.isEmpty()).isFalse();

    }



}