package aston.project.servlet.mapper;

import aston.project.servlet.dto.UserIncomingDto;
import aston.project.servlet.dto.UserOutGoingDto;
import aston.project.model.User;
import aston.project.servlet.dto.UserUpdateDto;

import java.util.List;

public interface UserDtoMapper {
    User map(UserIncomingDto userIncomingDto);

    User map(UserUpdateDto userIncomingDto);

    UserOutGoingDto map(User user);

    List<UserOutGoingDto> map(List<User> user);

}
