package com.fisa.infra.account.repository.jpa;


import java.util.Optional;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.account.dummy.DummyAccount;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private AccountRepository accountRepository;


	private Account account;


	@BeforeEach
	void setUp() {

		account = DummyAccount.dummy();

	}

	@DisplayName("로그인 아이디로 해당 회원 찾는 테스트")
	@Test
	void findAccountByLoginId() {

		//given
		entityManager.persist(account);

		//when
		Optional<Account> op =
			accountRepository.findAccountByLoginId("testId");

		//then
		Assertions.assertThat(op.get()).isNotNull();

	}
}