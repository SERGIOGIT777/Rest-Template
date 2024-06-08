package aston.project.service.impl;

import aston.project.servlet.dto.DepartmentIncomingDto;
import aston.project.servlet.dto.DepartmentOutGoingDto;
import aston.project.servlet.dto.DepartmentUpdateDto;
import aston.project.servlet.mapper.DepartmentDtoMapper;
import aston.project.servlet.mapper.impl.DepartmentDtoMapperImpl;
import aston.project.model.UserToDepartment;
import aston.project.repository.DepartmentRepository;
import aston.project.exception.NotFoundException;
import aston.project.model.Department;
import aston.project.repository.UserRepository;
import aston.project.repository.UserToDepartmentRepository;
import aston.project.repository.impl.DepartmentRepositoryImpl;
import aston.project.repository.impl.UserRepositoryImpl;
import aston.project.repository.impl.UserToDepartmentRepositoryImpl;
import aston.project.service.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository = DepartmentRepositoryImpl.getInstance();
    private final UserRepository userRepository = UserRepositoryImpl.getInstance();
    private final UserToDepartmentRepository userToDepartmentRepository = UserToDepartmentRepositoryImpl.getInstance();
    private static final DepartmentDtoMapper departmentDtoMapper = DepartmentDtoMapperImpl.getInstance();
    private static DepartmentService instance;


    private DepartmentServiceImpl() {
    }

    public static synchronized DepartmentService getInstance() {
        if (instance == null) {
            instance = new DepartmentServiceImpl();
        }
        return instance;
    }

    private void checkExistDepartment(Long departmentId) throws NotFoundException {
        if (!departmentRepository.exitsById(departmentId)) {
            throw new NotFoundException("Department not found.");
        }
    }

    @Override
    public DepartmentOutGoingDto save(DepartmentIncomingDto departmentDto) {
        Department department = departmentDtoMapper.map(departmentDto);
        department = departmentRepository.save(department);
        return departmentDtoMapper.map(department);
    }

    @Override
    public void update(DepartmentUpdateDto departmentUpdateDto) throws NotFoundException {
        checkExistDepartment(departmentUpdateDto.getId());
        Department department = departmentDtoMapper.map(departmentUpdateDto);
        departmentRepository.update(department);
    }

    @Override
    public DepartmentOutGoingDto findById(Long departmentId) throws NotFoundException {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() ->
                new NotFoundException("Department not found."));
        return departmentDtoMapper.map(department);
    }

    @Override
    public List<DepartmentOutGoingDto> findAll() {
        List<Department> departmentList = departmentRepository.findAll();
        return departmentDtoMapper.map(departmentList);
    }

    @Override
    public void delete(Long departmentId) throws NotFoundException {
        checkExistDepartment(departmentId);
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public void deleteUserFromDepartment(Long departmentId, Long userId) throws NotFoundException {
        checkExistDepartment(departmentId);
        if (userRepository.exitsById(userId)) {
            UserToDepartment linkUserDepartment = userToDepartmentRepository.findByUserIdAndDepartmentId(userId, departmentId)
                    .orElseThrow(() -> new NotFoundException("Link many to many Not found."));

            userToDepartmentRepository.deleteById(linkUserDepartment.getId());
        } else {
            throw new NotFoundException("User not found.");
        }

    }

    @Override
    public void addUserToDepartment(Long departmentId, Long userId) throws NotFoundException {
        checkExistDepartment(departmentId);
        if (userRepository.exitsById(userId)) {
            UserToDepartment linkUserDepartment = new UserToDepartment(
                    null,
                    userId,
                    departmentId
            );
            userToDepartmentRepository.save(linkUserDepartment);
        } else {
            throw new NotFoundException("User not found.");
        }

    }

}
