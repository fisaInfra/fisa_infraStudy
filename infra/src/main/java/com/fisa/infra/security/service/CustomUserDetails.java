package com.fisa.infra.security.service;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.role.domain.dto.RoleNameDTO;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final Long accountId;
    private final String loginId;
    private final String pwd;
    private boolean isDeleted;
    private Collection<SimpleGrantedAuthority> authorities;

	public CustomUserDetails(Account account, List<RoleNameDTO> roleNameDTO) {
		this.accountId = account.getAccountId();
		this.loginId = account.getLoginId();
		this.pwd = account.getPwd();
        this.isDeleted = account.isDeleted();
        this.authorities = toGrantedAuthority(roleNameDTO);

	}

    private Collection<SimpleGrantedAuthority> toGrantedAuthority(List<RoleNameDTO> roleNameDTOList) {
        Collection<SimpleGrantedAuthority> collections = new ArrayList<>();

        for (RoleNameDTO dto : roleNameDTOList) {
            String roleName = dto.getRoleName();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
            collections.add(simpleGrantedAuthority);

        }
        return collections;
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
