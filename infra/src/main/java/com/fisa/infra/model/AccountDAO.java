package com.fisa.infra.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fisa.infra.model.entity.Account;

@Repository
public interface AccountDAO extends JpaRepository<Account,String>{

}

