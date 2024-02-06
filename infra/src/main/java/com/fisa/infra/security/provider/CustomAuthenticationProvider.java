package com.fisa.infra.security.provider;

import com.fisa.infra.security.service.CustomUserDetails;
import com.fisa.infra.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String loginId = authentication.getName();

        CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(loginId);

        String pwd = (String) authentication.getCredentials();

        if (!userDetails.getUsername().equals(loginId) || !encoder.matches(pwd, userDetails.getPassword())){

           throw new BadCredentialsException("해당 회원의 매칭 정보가 올바르지 않습니다. 다시 확인해주세요");

        }

        return UsernamePasswordAuthenticationToken.authenticated(loginId, "", userDetails.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {

        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
