package com.fisa.infra.account.repository.jpa;


import com.fisa.infra.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account,Long> {

    Optional<Account> findAccountByLoginId(String loginId);
    
    Optional<Account> deleteAccountByLoginId(String loginId);
}

