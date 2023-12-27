package com.example.userservice.adapter.in.web;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.userservice.application.port.in.dto.RequestUserDTO;
import com.example.userservice.application.port.in.dto.ResponseUserDTO;
import com.example.userservice.application.usecase.LoadUserUseCase;
import com.example.userservice.common.mapper.UserDomainMapper;
import com.example.userservice.common.response.ResponseDTO;
import com.example.userservice.domain.User;
import com.example.userservice.domain.UserGrade;
import com.example.userservice.domain.UserRole;
import com.example.userservice.domain.UserStatus;
import com.example.userservice.domain.Visibilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import java.util.Locale;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(UserInfoController.class)
@MockBean(JpaMetamodelMappingContext.class)
class UserInfoControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  LoadUserUseCase loadUserUseCase;

  @Autowired
  ObjectMapper objectMapper;

  public RequestUserDTO makeUserInfo() {
    Faker faker = new Faker(Locale.KOREAN);
    String email = faker.internet().safeEmailAddress();
    String password = faker.internet().password();
    UserGrade grade = UserGrade.BRONZE;
    UserStatus status = UserStatus.ACTIVE;
    UserRole role = UserRole.USER;
    String name = faker.name().fullName();
    String nickname = faker.funnyName().name();
    String biography = faker.superhero().name();
    Visibilities visibility = Visibilities.OPENED;

    return new RequestUserDTO(
        email, password, grade, status, role, name, nickname, biography, visibility);
  }

  @Test
  @DisplayName("이용자 조회 Test")
  @WithMockUser
  void getUsers() throws Exception {

    //given
    RequestUserDTO requestUserDTO = makeUserInfo();
    User user = UserDomainMapper.INSTANCE.toUserDomain(requestUserDTO);
    ResponseUserDTO responseDTO = UserDomainMapper.INSTANCE.toResponseUserDTO(user);
    String uid = UUID.randomUUID().toString();

    given(loadUserUseCase.getUser(anyString())).willReturn(responseDTO);

    //when
    ResultActions testAction = mockMvc.perform(MockMvcRequestBuilders
            .get("/api/v1/users/" + uid)
            .with(csrf()))
        .andDo(print());

    //then
    ResponseDTO<ResponseUserDTO> responseUserDTO =
        ResponseDTO.success(loadUserUseCase.getUser(uid));

    testAction
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(responseUserDTO)));
  }

}
