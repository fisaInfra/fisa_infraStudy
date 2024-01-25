package com.fisa.infra.security.service;

import static com.fisa.infra.account.domain.entity.QAccount.*;

import java.util.List;
import java.util.Optional;

import com.fisa.infra.account.domain.dto.AccountRoleDTO;
import com.fisa.infra.account.domain.entity.Account;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fisa.infra.account.repository.jpa.AccountRepository;
import com.fisa.infra.role.domain.dto.RoleNameDTO;
import com.fisa.infra.role.repository.querydsl.inter.QueryAccountRoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final QueryAccountRoleRepository queryAccountRoleRepository;
    private final AccountRepository accountRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<RoleNameDTO> roles = queryAccountRoleRepository.findAccountRoleByLoginId(username);
        Optional<Account> optionalAccount = accountRepository.findAccountByLoginId(username);

        if (optionalAccount.isEmpty()){
            throw new BadCredentialsException("해당 로그인 아이디를 가진 회원이 존재하지 않습니다.");
        }

        Account account = optionalAccount.get();

        return new CustomUserDetails(account, roles);
    }
}
