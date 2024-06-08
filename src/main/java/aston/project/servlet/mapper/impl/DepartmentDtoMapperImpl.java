package aston.project.servlet.mapper.impl;

import aston.project.servlet.dto.UserSmallOutGoingDto;
import aston.project.servlet.dto.DepartmentIncomingDto;
import aston.project.servlet.dto.DepartmentOutGoingDto;
import aston.project.servlet.dto.DepartmentUpdateDto;
import aston.project.servlet.mapper.DepartmentDtoMapper;
import aston.project.model.Department;

import java.util.List;

public class DepartmentDtoMapperImpl implements DepartmentDtoMapper {
    private static DepartmentDtoMapper instance;

    private DepartmentDtoMapperImpl() {
    }

    public static synchronized DepartmentDtoMapper getInstance() {
        if (instance == null) {
            instance = new DepartmentDtoMapperImpl();
        }
        return instance;
    }

    @Override
    public Department map(DepartmentIncomingDto dto) {
        return new Department(
                null,
                dto.getName(),
                null
        );
    }

    @Override
    public DepartmentOutGoingDto map(Department department) {
        List<UserSmallOutGoingDto> userList = department.getUserList()
                .stream().map(user -> new UserSmallOutGoingDto(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName()
                )).toList();

        return new DepartmentOutGoingDto(
                department.getId(),
                department.getName(),
                userList
        );
    }

    @Override
    public Department map(DepartmentUpdateDto updateDto) {
        return new Department(
                updateDto.getId(),
                updateDto.getName(),
                null
        );
    }

    @Override
    public List<DepartmentOutGoingDto> map(List<Department> departmentList) {
        return departmentList.stream().map(this::map).toList();
    }

    @Override
    public List<Department> mapUpdateList(List<DepartmentUpdateDto> departmentList) {
        return departmentList.stream().map(this::map).toList();
    }
}