package aston.project.service;

import aston.project.servlet.dto.DepartmentIncomingDto;
import aston.project.servlet.dto.DepartmentOutGoingDto;
import aston.project.servlet.dto.DepartmentUpdateDto;
import aston.project.exception.NotFoundException;

import java.util.List;

public interface DepartmentService {
    DepartmentOutGoingDto save(DepartmentIncomingDto department);

    void update(DepartmentUpdateDto department) throws NotFoundException;

    DepartmentOutGoingDto findById(Long departmentId) throws NotFoundException;

    List<DepartmentOutGoingDto> findAll();

    void delete(Long departmentId) throws NotFoundException;

    void deleteUserFromDepartment(Long departmentId, Long userId) throws NotFoundException;

    void addUserToDepartment(Long departmentId, Long userId) throws NotFoundException;
}
