package aston.project.repository;

import aston.project.model.Department;
import aston.project.model.User;
import aston.project.model.UserToDepartment;

import java.util.List;
import java.util.Optional;

public interface UserToDepartmentRepository extends Repository<UserToDepartment, Long> {
    boolean deleteByUserId(Long id);

    boolean deleteByDepartmentId(Long departmentId);

    List<UserToDepartment> findAllByUserId(Long userId);

    List<Department> findDepartmentsByUserId(Long userId);

    List<UserToDepartment> findAllByDepartmentId(Long departmentId);

    List<User> findUsersByDepartmentId(Long departmentId);

    Optional<UserToDepartment> findByUserIdAndDepartmentId(Long userId, Long departmentId);
}
