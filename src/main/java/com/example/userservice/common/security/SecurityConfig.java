package com.example.userservice.common.security;

import com.example.userservice.application.service.event.UserLoginEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

  private final UserDetailsService userDetailsService;
  private final UserLoginEventService userLoginEventService;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .sessionManagement(sessionManagement ->
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )    // 세션을 사용하지 않으므로 STATELESS 설정
        .csrf(AbstractHttpConfigurer::disable)  //cross site request 는 쿠키를 기반한 인증방식, REST API에서는 사용안해도 무방
        .cors(AbstractHttpConfigurer::disable)
        .addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  // 비밀번호 암호화 모듈 추가
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // Authenticate 인증 메서드를 제공하는 매니저. provider의 인터페이스를 의미함.
  @Bean
  public AuthenticationManager authenticationManager() {
    return new ProviderManager(customAuthenticationProvider());
  }

  @Bean
  public UsernamePasswordAuthenticationFilter customAuthenticationFilter() {
    UsernamePasswordAuthenticationFilter customAuthenticationFilter =
        new CustomAuthenticationFilter(authenticationManager());
    customAuthenticationFilter.setRequiresAuthenticationRequestMatcher(
        new AntPathRequestMatcher("/api/v1/user/sign-in", HttpMethod.POST.name()));
    customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());

    return customAuthenticationFilter;
  }

  @Bean
  public SavedRequestAwareAuthenticationSuccessHandler customLoginSuccessHandler() {
    return new CustomLoginSuccessHandler(userLoginEventService);
  }

  @Bean
  public AuthenticationProvider customAuthenticationProvider() {
    return new CustomAuthenticationProvider(userDetailsService, passwordEncoder());
  }

}

