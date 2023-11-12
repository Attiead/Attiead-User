package com.example.userservice.common.security;

import com.example.userservice.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) {
    User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
    String token = TokenProvider.createToken(user.getUid());
    response.addHeader(HttpHeaders.AUTHORIZATION, AuthConstants.TOKEN_TYPE + " " + token);
  }
}
