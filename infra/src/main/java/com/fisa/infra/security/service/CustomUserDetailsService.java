package com.fisa.infra.security.service;

import java.util.Optional;

import com.fisa.infra.account.domain.entity.Account;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fisa.infra.account.repository.jpa.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Account> op = accountRepository.findAccountByLoginId(username);
        if (op.isEmpty()){
            throw new BadCredentialsException("해당 로그인 아이디를 가진 회원이 존재하지 않습니다.");
        }

        Account account = op.get();



        return new CustomUserDetails(account);
    }
}
