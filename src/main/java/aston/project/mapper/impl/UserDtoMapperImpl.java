package aston.project.mapper.impl;

import aston.project.dto.UserIncomingDto;
import aston.project.dto.UserOutGoingDto;
import aston.project.dto.UserUpdateDto;
import aston.project.mapper.DepartmentDtoMapper;
import aston.project.mapper.PhoneNumberDtoMapper;
import aston.project.mapper.RoleDtoMapper;
import aston.project.mapper.UserDtoMapper;
import aston.project.model.User;

import java.util.List;

public class UserDtoMapperImpl implements UserDtoMapper {
    private static final RoleDtoMapper roleDtoMapper = RoleDtoMapperImpl.getInstance();
    private static final PhoneNumberDtoMapper phoneNumberDtoMapper = PhoneNumberDtoMapperImpl.getInstance();
    private static final DepartmentDtoMapper departmentDtoMapper = DepartmentDtoMapperImpl.getInstance();


    private static UserDtoMapper instance;

    private UserDtoMapperImpl() {
    }

    public static synchronized UserDtoMapper getInstance() {
        if (instance == null) {
            instance = new UserDtoMapperImpl();
        }
        return instance;
    }

    @Override
    public User map(UserIncomingDto userDto) {
        return new User(
                null,
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getRole(),
                null,
                null
        );
    }

    @Override
    public User map(UserUpdateDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                roleDtoMapper.map(userDto.getRole()),
                phoneNumberDtoMapper.mapUpdateList(userDto.getPhoneNumberList()),
                departmentDtoMapper.mapUpdateList(userDto.getDepartmentList())
        );
    }

    @Override
    public UserOutGoingDto map(User user) {
        return new UserOutGoingDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                roleDtoMapper.map(user.getRole()),
                phoneNumberDtoMapper.map(user.getPhoneNumberList()),
                departmentDtoMapper.map(user.getDepartmentList())
        );
    }

    @Override
    public List<UserOutGoingDto> map(List<User> user) {
        return user.stream().map(this::map).toList();
    }
}
