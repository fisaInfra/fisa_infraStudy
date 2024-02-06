package com.fisa.infra.role.repository.querydsl.inter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fisa.infra.account.domain.dto.AccountRoleDTO;
import com.fisa.infra.account.domain.entity.Account;
import com.fisa.infra.account.dummy.DummyAccount;
import com.fisa.infra.role.domain.entity.AccountRole;
import com.fisa.infra.role.domain.entity.Role;
import com.fisa.infra.role.dummy.DummyAccountRole;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class QueryAccountRoleRepositoryTest {


	@Autowired
	EntityManager entityManager;

	@Autowired
	QueryAccountRoleRepository accountRoleRepository;

	private Account userAccount;

	private Role userRole;
	private Role adminRole;
	private AccountRole accountRole;


	@BeforeEach
	void setUp() {

		userAccount = DummyAccount.dummy();

		adminRole = Role.createRole("ROLE_ADMIN");
		userRole = Role.createRole("ROLE_USER");


	}

	@Test
	@DisplayName("로그인 아이디를 이용해서 해당 회원의 정보를 가져오는 테스트")
	void findAccountRoleByLoginId() {

		//given
		String loginId = userAccount.getLoginId();

		entityManager.persist(userAccount);
		entityManager.persist(userRole);

		accountRole = DummyAccountRole.dummy(userAccount, userRole);
		entityManager.persist(accountRole);

		//when
		Optional<AccountRoleDTO> op = accountRoleRepository.findAccountRoleByLoginId(loginId);


		//then
		Assertions.assertThat(op.get()).isNotNull();


	}
}