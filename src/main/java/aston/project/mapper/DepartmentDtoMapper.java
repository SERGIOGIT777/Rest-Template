package aston.project.mapper;

import aston.project.dto.DepartmentIncomingDto;
import aston.project.dto.DepartmentOutGoingDto;
import aston.project.dto.DepartmentUpdateDto;
import aston.project.model.Department;

import java.util.List;

public interface DepartmentDtoMapper {
    Department map(DepartmentIncomingDto departmentIncomingDto);

    DepartmentOutGoingDto map(Department department);

    Department map(DepartmentUpdateDto departmentUpdateDto);

    List<DepartmentOutGoingDto> map(List<Department> departmentList);

    List<Department> mapUpdateList(List<DepartmentUpdateDto> departmentList);
}
