package com.fisa.infra.role.repository.querydsl;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.fisa.infra.role.domain.entity.Role;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class QueryRoleRepositoryTest {

	@Autowired
	EntityManager entityManager;

	@Autowired
	RoleRepository roleRepository;


	private Role user;
	private Role admin;

	@BeforeEach
	void setUp() {

		admin=Role.createRole("ROLE_ADMIN");
		user=Role.createRole("ROLE_USER");


	}

	@Test
	@DisplayName("id값으로 Role 찾아오는 테스트")
	void findById() {

		entityManager.persist(admin);
		entityManager.persist(user);

		Optional<Role> opAdmin = roleRepository.findById(1L);
		Optional<Role> opUser = roleRepository.findById(2L);

		assertThat(opAdmin.isEmpty()).isFalse();
		assertThat(opUser.isEmpty()).isFalse();


		assertThat(opAdmin.get().getName()).isEqualTo("ROLE_ADMIN");
		assertThat(opUser.get().getName()).isEqualTo("ROLE_USER");
	}
}