package com.example.userservice.common.mapper;

import com.example.userservice.adapter.out.persistence.UserEntity;
import com.example.userservice.domain.User;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(
    componentModel = "spring"
)
public interface UserEntityMapper {

    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);

    User toUserDomain(UserEntity userEntity);

    @Mapping(target = "uid", ignore = true)
    @Mapping(target = "grade", source = "grade", defaultValue = "BRONZE")
    @Mapping(target = "status", source = "status", defaultValue = "ACTIVE")
    @Mapping(target = "role", source = "role", defaultValue = "STUDENT")
    @Mapping(target = "visibility", source = "visibility", defaultValue = "OPENED")
    UserEntity toUserEntity(User user);

    default UUID generateDefaultUUID(UUID value) {
        return value != null ? value : UUID.randomUUID();
    }

}
