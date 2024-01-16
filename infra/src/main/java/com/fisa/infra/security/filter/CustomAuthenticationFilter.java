package com.fisa.infra.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /*
    * @author hwiju yeom
    * authmanager로 해당 비인가 객체를 생성해서 넘길 때 사용하는 메서드입니다.
    * */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        return super.attemptAuthentication(request, response);
    }
    /*
     * @author hwiju yeom
     *
     * 모든 확인을 마치고 제대로된 사용자일 경우에 해당 메서드가 사용됩니다.
     * 이때 이곳에서 Security context에 해당 인증 사용자 객체를 주입해줍니다.
     * */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
    }


    /*
     * @author hwiju yeom
     *
     * 모든 확인을 마치고 잘못된 사용자 로그인 요청일 경우에 해당 메서드가 사용됩니다.
     * 이때 이곳에서 redirect를 해줄지 여부등을 결정해서 로그인 실패 사용자에게 화면 처리해줍니다.
     * */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }


}
