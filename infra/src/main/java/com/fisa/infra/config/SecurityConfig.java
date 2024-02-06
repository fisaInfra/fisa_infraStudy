package com.fisa.infra.config;


import com.fisa.infra.account.repository.jpa.AccountRepository;
import com.fisa.infra.security.filter.CustomAuthenticationFilter;
import com.fisa.infra.security.provider.CustomAuthenticationProvider;
import com.fisa.infra.security.service.CustomUserDetailsService;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    private final CustomUserDetailsService customUserDetailsService;

    //TODO: 시큐리티 기본 설정 끝, EntryPoint 사용 예정 -> Security Exception Handling 작업

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        //security 관련 api url 일단 권한 다 열어둠
        http.authorizeHttpRequests(auth -> {
            auth.anyRequest().permitAll();
        });

        // 불필요한 설정 꺼둠
        http.cors(corsConfigurer -> corsConfigurer.disable());
        http.csrf(csrfConfigurer -> csrfConfigurer.disable());
        http.headers(headerConfig -> headerConfig.frameOptions(
                frameOptionsConfig -> frameOptionsConfig.disable()));


        //우리가 만들어 사용할 form login 설정
        http.formLogin(httpSecurityFormLoginConfigurer -> {
            httpSecurityFormLoginConfigurer.loginPage("/api/account/login");
            httpSecurityFormLoginConfigurer.passwordParameter("pwd");
            httpSecurityFormLoginConfigurer.usernameParameter("loginId");
        });

        // 세션 유지를 위한 기능 사용
        http.rememberMe(rememberMecConfig -> {
            rememberMecConfig.rememberMeParameter("remember");
            rememberMecConfig.alwaysRemember(false);//체크박스 사용 없이도 늘 활성화 시키기
            rememberMecConfig.userDetailsService(customUserDetailsService);
        });

        http.logout(logout -> {logout.logoutUrl("/account/logout");
            logout.logoutSuccessUrl("/");
            logout.invalidateHttpSession(true); // 로그아웃 후 JSESSIONID 이름의 쿠키값 삭제
            logout.deleteCookies("JSESSIONID", "remember-me");
        });

        // 스프링 시큐리티가 지원하는 세션을 사용하겠다는 의미 사용 안 하려면 STATELESS 사용
        http.sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
        http.addFilterAt(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public UsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {

        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager(null));

        // 해당 부분은 Custom한 AuthenticationManager를 사용해도 되지만 굳이 사용하지 않아도 되기 떄문에 설정을 이렇게 진행해줌
        customAuthenticationFilter.setAuthenticationManager(new ProviderManager(customAuthenticationProvider()));

        return customAuthenticationFilter;
    }

    private AuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider(customUserDetailsService, bCryptPasswordEncoder());
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();

    }

    /*
    * 비밀번호 평문 저장을 막기 위해 사용합니다.
    * */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
