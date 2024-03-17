package com.example.userservice.adapter.in.web;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
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
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(UserUpdateController.class)
@AutoConfigureRestDocs
class UserUpdateControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  UpdateUserUseCase updateUserUseCase;

  @Autowired
  ObjectMapper objectMapper;

  public RequestUpdateUserDTO makeUserInfo() {
    Faker faker = new Faker(Locale.KOREAN);
    String uid = UUID.randomUUID().toString();
    String password = faker.internet().password();
    UserGrade grade = UserGrade.BRONZE;
    UserStatus status = UserStatus.ACTIVE;
    UserRole role = UserRole.USER;
    String name = faker.name().fullName();
    String nickname = faker.funnyName().name();
    String biography = faker.superhero().name();
    Visibilities visibility = Visibilities.OPENED;

    return new RequestUpdateUserDTO(
        uid, password, grade, status, role, name, nickname, biography, visibility);
  }

  @Test
  @DisplayName("이용자 수정 Test")
  @WithMockUser
  void updateUsers() throws Exception {

    // given
    RequestUpdateUserDTO requestUpdateUserDTO = makeUserInfo();
    User user = UserDomainMapper.INSTANCE.toUserDomain(requestUpdateUserDTO);
    User responseUser = User.builder()
        .uid(user.getUid())
        .email(new Faker(Locale.KOREAN).internet().safeEmailAddress())
        .password(user.getPassword())
        .grade(user.getGrade())
        .status(user.getStatus())
        .role(user.getRole())
        .name(user.getName())
        .nickname(user.getNickname())
        .biography(user.getBiography())
        .visibility(user.getVisibility())
        .build();

    ResponseUserDTO responseDTO = UserDomainMapper.INSTANCE.toResponseUserDTO(responseUser);

    given(updateUserUseCase.update(any())).willReturn(responseDTO);

    // when
    ResultActions testAction = mockMvc.perform(RestDocumentationRequestBuilders
            .put("/api/v1/users")
            .with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestUpdateUserDTO)))
        .andDo(print());

    // then
    ResponseDTO<ResponseUserDTO> responseUserDTO =
        ResponseDTO.success(updateUserUseCase.update(requestUpdateUserDTO));

    testAction.andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(responseUserDTO)))
        .andDo(document("이용자 수정 TEST",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            resource(ResourceSnippetParameters.builder()
                .tag("User Update TEST API")
                .summary("이용자 수정 TEST API")
                .requestFields(
                    fieldWithPath("uid").type(JsonFieldType.STRING).description("UID"),
                    fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                    fieldWithPath("grade").type(JsonFieldType.STRING).description("등급"),
                    fieldWithPath("status").type(JsonFieldType.STRING).description("상태"),
                    fieldWithPath("role").type(JsonFieldType.STRING).description("권한"),
                    fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                    fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임"),
                    fieldWithPath("biography").type(JsonFieldType.STRING).description("추가정보"),
                    fieldWithPath("visibility").type(JsonFieldType.STRING).description("공개상태"))
                .responseFields(
                    fieldWithPath("data").type(JsonFieldType.OBJECT).description("data").optional(),
                    fieldWithPath("data.uid").type(JsonFieldType.STRING).description("UID"),
                    fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
                    fieldWithPath("data.password").type(JsonFieldType.STRING).description("비밀번호"),
                    fieldWithPath("data.grade").type(JsonFieldType.STRING).description("등급"),
                    fieldWithPath("data.status").type(JsonFieldType.STRING).description("상태"),
                    fieldWithPath("data.role").type(JsonFieldType.STRING).description("권한"),
                    fieldWithPath("data.name").type(JsonFieldType.STRING).description("이름"),
                    fieldWithPath("data.nickname").type(JsonFieldType.STRING).description("닉네임"),
                    fieldWithPath("data.biography").type(JsonFieldType.STRING).description("추가정보"),
                    fieldWithPath("data.visibility").type(JsonFieldType.STRING).description("공개상태"),
                    fieldWithPath("meta").type(JsonFieldType.OBJECT).description("meta"),
                    fieldWithPath("meta.type").type(JsonFieldType.STRING).description("MetaCode name"),
                    fieldWithPath("meta.code").type(JsonFieldType.STRING).description("MetaCode"),
                    fieldWithPath("meta.message").type(JsonFieldType.STRING).description("message").optional())
                .build())));

  }
}
