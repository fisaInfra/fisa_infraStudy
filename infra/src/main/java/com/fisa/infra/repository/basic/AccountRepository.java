package com.fisa.infra.repository.basic;


import com.fisa.infra.domain.entity.Account;
import com.fisa.infra.repository.common.CommonAccountRepository;
import org.springframework.data.repository.Repository;


public interface AccountRepository extends Repository<Account,Long>, CommonAccountRepository {

}

