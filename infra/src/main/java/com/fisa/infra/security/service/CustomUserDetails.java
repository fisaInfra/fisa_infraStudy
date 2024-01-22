package com.fisa.infra.security.service;

import com.fisa.infra.account.domain.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final String accountId;
    private final String accountName;
    private final String loginId;
    private final String pwd;
    private boolean isDeleted;
    private Collection<GrantedAuthority> authorities;


    public CustomUserDetails(Account account){

        this.accountId = String.valueOf(account.getAccountId());
        this.accountName = account.getName();
        this.loginId = account.getLoginId();
        this.pwd= account.getPwd();
        this.isDeleted = account.getIsDeleted();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.pwd;
    }

    @Override
    public String getUsername() {
        return this.loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isDeleted;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isDeleted;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isDeleted;
    }

    @Override
    public boolean isEnabled() {
        return this.isDeleted;
    }
}
