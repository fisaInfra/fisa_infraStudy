package com.fisa.infra.account.repository.basic;


import com.fisa.infra.account.domain.Account;

/*
* 해당 인터페이스를 통해서 메서드를 명시할 때 사용합니다.
* */
public interface CommonAccountRepository {

    Account findById(Long id);

}
