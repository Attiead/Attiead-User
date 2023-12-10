package com.example.userservice.common.mapper;

import com.example.userservice.application.port.in.dto.RequestUpdateUserDto;
import com.example.userservice.application.port.in.dto.RequestUserDto;
import com.example.userservice.application.port.in.dto.ResponseUserDto;
import com.example.userservice.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.control.MappingControl.Use;
import org.mapstruct.factory.Mappers;

@Mapper(
    componentModel = "spring"
)
public interface UserDomainMapper {

  UserDomainMapper INSTANCE = Mappers.getMapper(UserDomainMapper.class);

  ResponseUserDto toResponseUserDto(User user);

  @Mapping(target = "uid", ignore = true)
  User requestUserDtoToUserDomain(RequestUserDto requestUserDto);

  User requestUpdateUserDtoToUserDomain(RequestUpdateUserDto requestUpdateUserDto);

}
