package com.fisa.infra.account.repository.querydsl;

import com.fisa.infra.account.domain.dto.AccountDTO;

import java.util.Optional;

public interface QueryAccountRepository {
    Optional<AccountDTO> queryFindAccountByLoginId(String id);
}
