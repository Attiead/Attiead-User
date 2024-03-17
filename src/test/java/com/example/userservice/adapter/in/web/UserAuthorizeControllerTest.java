package com.example.userservice.adapter.in.web;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.example.userservice.application.usecase.AuthorizeUserUseCase;
import com.example.userservice.common.exception.InvalidJwtTokenException;
import com.example.userservice.common.response.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;
import javax.crypto.SecretKey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(UserAuthorizeController.class)
//@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class UserAuthorizeControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  AuthorizeUserUseCase authorizeUserUseCase;

  @Autowired
  ObjectMapper objectMapper;

  private final long jwtExpired = 3600;

  private final String jwtSecretkey = "8EF6D35BC965E6BC62A225B3B23E398E";

  private String createToken(String uid) {
    Instant issueAt = Instant.now();

    JwtBuilder jwtBuilder = Jwts.builder()
        .setIssuedAt(Date.from(issueAt))
        .setExpiration(Date.from(issueAt.plus(jwtExpired, ChronoUnit.MINUTES)));

    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    Key signingKey = Keys.hmacShaKeyFor(jwtSecretkey.getBytes(StandardCharsets.UTF_8));

    return jwtBuilder.claim("uid", uid)
        .signWith(signingKey, signatureAlgorithm)
        .compact();
  }

  private String validToken(String jwt) {
    try {
      SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecretkey.getBytes(StandardCharsets.UTF_8));

      return (String) Jwts.parserBuilder()
          .setSigningKey(secretKey)
          .build()
          .parseClaimsJws(jwt)
          .getBody()
          .get("uid");
    } catch (Exception exception) {
      throw new InvalidJwtTokenException("Invalid Token");
    }
  }

  @Test
  @DisplayName("이용자 인가 Test")
  @WithMockUser
  void authorizeUsers() throws Exception {

    //given
    String uid = UUID.randomUUID().toString();
    String token = createToken(uid);
    String returnUid = validToken(token);

    given(authorizeUserUseCase.verifyUser(any())).willReturn(returnUid);

    //when
    ResultActions testAction = mockMvc.perform(RestDocumentationRequestBuilders
            .get("/api/v1/users/authorize")
            .header(HttpHeaders.AUTHORIZATION, token)
            .with(csrf()))
        .andDo(print());

    //then
    ResponseDTO<String> responseUserDTO = ResponseDTO.success(returnUid);

    testAction
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(responseUserDTO)))
        .andDo(document("이용자 토큰 인가 TEST",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            resource(ResourceSnippetParameters.builder()
                .tag("User Authorize TEST API")
                .summary("이용자 토큰 인가 TEST API")
                .responseFields(
                    fieldWithPath("data").type(JsonFieldType.STRING).description("uid").optional(),
                    fieldWithPath("meta").type(JsonFieldType.OBJECT).description("meta"),
                    fieldWithPath("meta.type").type(JsonFieldType.STRING).description("MetaCode name"),
                    fieldWithPath("meta.code").type(JsonFieldType.STRING).description("MetaCode"),
                    fieldWithPath("meta.message").type(JsonFieldType.STRING).description("message").optional())
                .build())));

  }

}
