package com.antologic.project.mappers;

import com.antologic.project.dtos.UserDto;
import com.antologic.project.entity.User;
import com.antologic.project.forms.CreateUserForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface UserMapper {

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "uuid")
    @Mapping(ignore = true, target = "projects")
    @Mapping(ignore = true, target = "timesheets")
    User createUserFormToUser(CreateUserForm createUserForm);

    UserDto userToUserDto(User User);
}
