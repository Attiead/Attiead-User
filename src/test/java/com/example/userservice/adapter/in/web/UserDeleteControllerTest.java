package com.example.userservice.adapter.in.web;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.anyString;
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
import com.example.userservice.application.usecase.DeleteUserUseCase;
import com.example.userservice.common.response.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(UserDeleteController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class UserDeleteControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  DeleteUserUseCase deleteUserUseCase;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  @DisplayName("이용자 삭제 Test")
  @WithMockUser
  void deleteUsers() throws Exception {

    //given
    String uid = UUID.randomUUID().toString();
    willDoNothing().given(deleteUserUseCase).delete(anyString());

    //when
    ResultActions testAction = mockMvc.perform(RestDocumentationRequestBuilders
            .delete("/api/v1/users/{uid}", uid)
            .with(csrf()))
        .andDo(print());

    //then
    ResponseDTO<String> responseUserDTO = ResponseDTO.success(uid);

    testAction
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(responseUserDTO)))
        .andDo(document("이용자 삭제 TEST",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            resource(ResourceSnippetParameters.builder()
                .tag("User Delete TEST API")
                .summary("이용자 삭제 TEST API")
                .pathParameters(
                    parameterWithName("uid").description("UID"))
                .responseFields(
                    fieldWithPath("data").type(JsonFieldType.STRING).description("uid").optional(),
                    fieldWithPath("meta").type(JsonFieldType.OBJECT).description("meta"),
                    fieldWithPath("meta.type").type(JsonFieldType.STRING).description("MetaCode name"),
                    fieldWithPath("meta.code").type(JsonFieldType.STRING).description("MetaCode"),
                    fieldWithPath("meta.message").type(JsonFieldType.STRING).description("message").optional())
                .build())));

  }

}
