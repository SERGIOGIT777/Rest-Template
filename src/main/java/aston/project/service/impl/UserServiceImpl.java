package aston.project.service.impl;

import aston.project.servlet.dto.UserIncomingDto;
import aston.project.servlet.dto.UserOutGoingDto;
import aston.project.servlet.dto.UserUpdateDto;
import aston.project.servlet.mapper.UserDtoMapper;
import aston.project.model.User;
import aston.project.service.UserService;
import aston.project.exception.NotFoundException;
import aston.project.servlet.mapper.impl.UserDtoMapperImpl;
import aston.project.repository.UserRepository;
import aston.project.repository.impl.UserRepositoryImpl;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository = UserRepositoryImpl.getInstance();
    private static final UserDtoMapper userDtoMapper = UserDtoMapperImpl.getInstance();
    private static UserService instance;


    private UserServiceImpl() {
    }

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    private void checkExistUser(Long userId) throws NotFoundException {
        if (!userRepository.exitsById(userId)) {
            throw new NotFoundException("User not found.");
        }
    }

    @Override
    public UserOutGoingDto save(UserIncomingDto userDto) {
        User user = userRepository.save(userDtoMapper.map(userDto));
        return userDtoMapper.map(userRepository.findById(user.getId()).orElse(user));
    }

    @Override
    public void update(UserUpdateDto userDto) throws NotFoundException {
        if (userDto == null || userDto.getId() == null) {
            throw new IllegalArgumentException();
        }
        checkExistUser(userDto.getId());
        userRepository.update(userDtoMapper.map(userDto));
    }

    @Override
    public UserOutGoingDto findById(Long userId) throws NotFoundException {
        checkExistUser(userId);
        User user = userRepository.findById(userId).orElseThrow();
        return userDtoMapper.map(user);
    }

    @Override
    public List<UserOutGoingDto> findAll() {
        List<User> all = userRepository.findAll();
        return userDtoMapper.map(all);
    }

    @Override
    public void delete(Long userId) throws NotFoundException {
        checkExistUser(userId);
        userRepository.deleteById(userId);
    }
}
