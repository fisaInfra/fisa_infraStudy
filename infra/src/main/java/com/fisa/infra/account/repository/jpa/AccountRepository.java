package com.fisa.infra.account.repository.jpa;


import com.fisa.infra.account.repository.basic.CommonAccountRepository;
import com.fisa.infra.account.domain.entity.Account;
import org.springframework.data.repository.Repository;


public interface AccountRepository extends Repository<Account,Long>, CommonAccountRepository {

}

