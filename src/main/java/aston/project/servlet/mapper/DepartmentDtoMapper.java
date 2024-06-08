package aston.project.servlet.mapper;

import aston.project.servlet.dto.DepartmentIncomingDto;
import aston.project.servlet.dto.DepartmentOutGoingDto;
import aston.project.servlet.dto.DepartmentUpdateDto;
import aston.project.model.Department;

import java.util.List;

public interface DepartmentDtoMapper {
    Department map(DepartmentIncomingDto departmentIncomingDto);

    DepartmentOutGoingDto map(Department department);

    Department map(DepartmentUpdateDto departmentUpdateDto);

    List<DepartmentOutGoingDto> map(List<Department> departmentList);

    List<Department> mapUpdateList(List<DepartmentUpdateDto> departmentList);
}
