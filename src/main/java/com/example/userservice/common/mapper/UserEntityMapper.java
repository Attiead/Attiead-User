package com.example.userservice.common.mapper;

import com.example.userservice.adapter.out.persistence.UserEntity;
import com.example.userservice.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
    componentModel = "spring"
)
public interface UserEntityMapper {

    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);

    User toUserDomain(UserEntity userEntity);

    UserEntity toUserEntity(User user);

}
