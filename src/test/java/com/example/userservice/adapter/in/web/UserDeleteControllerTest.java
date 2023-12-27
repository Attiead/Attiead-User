package com.example.userservice.adapter.in.web;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.userservice.application.usecase.DeleteUserUseCase;
import com.example.userservice.common.response.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@WebMvcTest(UserDeleteController.class)
@MockBean(JpaMetamodelMappingContext.class)
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
    ResultActions testAction = mockMvc.perform(MockMvcRequestBuilders
            .delete("/api/v1/users/" + uid)
            .with(csrf()))
        .andDo(print());

    //then
    ResponseDTO<String> responseUserDTO = ResponseDTO.success(uid);

    testAction
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(responseUserDTO)));
  }

}
