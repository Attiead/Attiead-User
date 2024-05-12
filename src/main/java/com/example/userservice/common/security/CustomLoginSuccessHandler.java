package com.example.userservice.common.security;

import com.example.userservice.application.service.event.UserLoginEventService;
import com.example.userservice.application.service.event.dto.UserLoginEventDTO;
import com.example.userservice.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.ZonedDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@RequiredArgsConstructor
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  private final UserLoginEventService userLoginEventService;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws JsonProcessingException {
    User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
    String token = TokenProvider.createToken(user.getUid());
    response.addHeader(HttpHeaders.AUTHORIZATION, AuthConstants.TOKEN_TYPE + " " + token);
    userLoginEventService.publish(UserLoginEventDTO.builder()
        .uid(user.getUid())
        .loginTime(String.valueOf(ZonedDateTime.now()))
        .build());
  }
}
