package aston.project.mapper;

import aston.project.dto.UserIncomingDto;
import aston.project.dto.UserOutGoingDto;
import aston.project.model.User;
import aston.project.dto.UserUpdateDto;

import java.util.List;

public interface UserDtoMapper {
    User map(UserIncomingDto userIncomingDto);

    User map(UserUpdateDto userIncomingDto);

    UserOutGoingDto map(User user);

    List<UserOutGoingDto> map(List<User> user);

}
