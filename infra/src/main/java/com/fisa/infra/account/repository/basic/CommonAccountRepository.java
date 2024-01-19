package com.fisa.infra.account.repository.basic;


import java.util.Optional;

import javax.swing.text.html.Option;

import com.fisa.infra.account.domain.entity.Account;

/*
* 해당 인터페이스를 통해서 메서드를 명시할 때 사용합니다.
* */
public interface CommonAccountRepository {

    Account findById(Long id);

	Optional<Account> findByLoginId(String username);
}
