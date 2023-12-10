package com.example.userservice.common.mapper;

import com.example.userservice.application.port.in.dto.RequestUpdateUserDTO;
import com.example.userservice.application.port.in.dto.RequestUserDTO;
import com.example.userservice.application.port.in.dto.ResponseUserDTO;
import com.example.userservice.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(
    componentModel = "spring"
)
public interface UserDomainMapper {

  UserDomainMapper INSTANCE = Mappers.getMapper(UserDomainMapper.class);

  ResponseUserDTO toResponseUserDTO(User user);

  @Mapping(target = "uid", ignore = true)
  User toUserDomain(RequestUserDTO requestUserDTO);

  User toUserDomain(RequestUpdateUserDTO requestUpdateUserDTO);

}

