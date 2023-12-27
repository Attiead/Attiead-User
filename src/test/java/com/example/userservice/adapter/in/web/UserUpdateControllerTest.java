package com.example.userservice.adapter.in.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.userservice.application.port.in.dto.RequestUpdateUserDTO;
import com.example.userservice.application.port.in.dto.ResponseUserDTO;
import com.example.userservice.application.usecase.UpdateUserUseCase;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(UserUpdateController.class)
@MockBean(JpaMetamodelMappingContext.class)
class UserUpdateControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  UpdateUserUseCase updateUserUseCase;

  @Autowired
  ObjectMapper objectMapper;

  public RequestUpdateUserDTO makeUserInfo() {
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

    return new RequestUpdateUserDTO(
        email, password, grade, status, role, name, nickname, biography, visibility);
  }

  @Test
  @DisplayName("이용자 수정 Test")
  @WithMockUser
  void updateUsers() throws Exception {

    // given
    RequestUpdateUserDTO requestUpdateUserDTO = makeUserInfo();
    User user = UserDomainMapper.INSTANCE.toUserDomain(requestUpdateUserDTO);
    ResponseUserDTO responseDTO = UserDomainMapper.INSTANCE.toResponseUserDTO(user);

    given(updateUserUseCase.update(any())).willReturn(responseDTO);

    // when
    ResultActions testAction = mockMvc.perform(MockMvcRequestBuilders
            .put("/api/v1/users")
            .with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestUpdateUserDTO)))
        .andDo(print());

    // then
    ResponseDTO<ResponseUserDTO> responseUserDTO =
        ResponseDTO.success(updateUserUseCase.update(requestUpdateUserDTO));

    testAction.andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(responseUserDTO)));
  }
}
