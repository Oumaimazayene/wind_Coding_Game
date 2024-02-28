package com.demo.demo.mappers;

import com.demo.demo.dtos.UserDTo;
import com.demo.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User ToUserDTo(UserDTo userDTo);
    UserDTo ToUsers(User user);

    void updateUserFromDTO(UserDTo userDTo,@MappingTarget User existingUser);
}
