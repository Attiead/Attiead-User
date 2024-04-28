package com.example.userservice.common.security;

import com.example.userservice.application.service.event.UserLoginEventService;
import com.example.userservice.application.service.event.dto.UserLoginEventDTO;
import com.example.userservice.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@RequiredArgsConstructor
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  private final UserLoginEventService userLoginEventService;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) {
    User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
    String token = TokenProvider.createToken(user.getUid());
    response.addHeader(HttpHeaders.AUTHORIZATION, AuthConstants.TOKEN_TYPE + " " + token);
    userLoginEventService.publish(UserLoginEventDTO.builder()
        .uid(user.getUid())
        .loginTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
        .build());
  }
}
