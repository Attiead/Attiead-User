package com.example.userservice.common.security;

import com.example.userservice.application.port.in.dto.UserAccountDTO;
import com.example.userservice.common.exception.ErrorMessages;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.io.IOException;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
    super.setAuthenticationManager(authenticationManager);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {
    UsernamePasswordAuthenticationToken authRequest;
    try {
      UserAccountDTO user = new ObjectMapper().readValue(request.getInputStream(),
          UserAccountDTO.class);
      authRequest = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
    } catch (IOException exception) {
      throw new UsernameNotFoundException(ErrorMessages.USERINFONOTMATCH.message);
    }

    setDetails(request, authRequest);
    return this.getAuthenticationManager().authenticate(authRequest);
  }

}
