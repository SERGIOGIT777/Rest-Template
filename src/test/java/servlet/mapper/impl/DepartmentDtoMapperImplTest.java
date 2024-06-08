package servlet.mapper.impl;

import aston.project.servlet.dto.DepartmentIncomingDto;
import aston.project.servlet.dto.DepartmentOutGoingDto;
import aston.project.servlet.dto.DepartmentUpdateDto;
import aston.project.servlet.mapper.DepartmentDtoMapper;
import aston.project.servlet.mapper.impl.DepartmentDtoMapperImpl;
import aston.project.model.Department;
import aston.project.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class DepartmentDtoMapperImplTest {
    private DepartmentDtoMapper departmentDtoMapper;

    @BeforeEach
    void setUp() {
        departmentDtoMapper = DepartmentDtoMapperImpl.getInstance();
    }

    @DisplayName("Department map(DepartmentIncomingDto")
    @Test
    void mapIncoming() {
        DepartmentIncomingDto dto = new DepartmentIncomingDto("New Department");
        Department result = departmentDtoMapper.map(dto);

        Assertions.assertNull(result.getId());
        Assertions.assertEquals(dto.getName(), result.getName());
    }

    @DisplayName("DepartmentOutGoingDto map(Department")
    @Test
    void testMapOutgoing() {
        Department department = new Department(100L, "Department #100", List.of(new User(), new User()));

        DepartmentOutGoingDto result = departmentDtoMapper.map(department);

        Assertions.assertEquals(department.getId(), result.getId());
        Assertions.assertEquals(department.getName(), result.getName());
        Assertions.assertEquals(department.getUserList().size(), result.getUserList().size());
    }

    @DisplayName("Department map(DepartmentUpdateDto")
    @Test
    void testMapUpdate() {
        DepartmentUpdateDto dto = new DepartmentUpdateDto(10L, "Update name.");

        Department result = departmentDtoMapper.map(dto);
        Assertions.assertEquals(dto.getId(), result.getId());
        Assertions.assertEquals(dto.getName(), result.getName());
    }

    @DisplayName("List<DepartmentOutGoingDto> map(List<Department>")
    @Test
    void testMap2() {
        List<Department> departmentList = List.of(
                new Department(1L, "dep 1", List.of()),
                new Department(2L, "dep 2", List.of()),
                new Department(3L, "dep 3", List.of())
        );

        List<DepartmentOutGoingDto> result = departmentDtoMapper.map(departmentList);

        Assertions.assertEquals(3, result.size());
    }

    @DisplayName("List<Department> mapUpdateList(List<DepartmentUpdateDto>")
    @Test
    void mapUpdateList() {
        List<DepartmentUpdateDto> departmentList = List.of(
                new DepartmentUpdateDto(),
                new DepartmentUpdateDto(),
                new DepartmentUpdateDto()
        );

        List<Department> result = departmentDtoMapper.mapUpdateList(departmentList);

        Assertions.assertEquals(3, result.size());
    }
}
