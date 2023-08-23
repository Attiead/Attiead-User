package com.example.userservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AbstractAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String userEmail = (String)token.getPrincipal();
        String userPw = (String) token.getCredentials();

        CustomUserDetails userDetailsDto = (CustomUserDetails) userDetailsService.loadUserByUsername(userEmail);
//        if (!passwordEncoder.matches(userPw, userDetailsDto.getUser().getPassword())) {
        if (!userPw.equals(userDetailsDto.getUser().getPassword())) {
            throw new BadCredentialsException(userDetailsDto.getName() + "Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetailsDto, userPw, userDetailsDto.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
