package aston.project.service;

import aston.project.dto.UserIncomingDto;
import aston.project.dto.UserOutGoingDto;
import aston.project.dto.UserUpdateDto;
import aston.project.exception.NotFoundException;

import java.util.List;

public interface UserService {
    UserOutGoingDto save(UserIncomingDto userDto);

    void update(UserUpdateDto userDto) throws NotFoundException;

    UserOutGoingDto findById(Long userId) throws NotFoundException;

    List<UserOutGoingDto> findAll();

    void delete(Long userId) throws NotFoundException;
}
