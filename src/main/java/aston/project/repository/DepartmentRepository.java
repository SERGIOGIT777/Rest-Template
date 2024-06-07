package aston.project.repository;

import aston.project.model.Department;

public interface DepartmentRepository extends Repository<Department, Long> {
    boolean exitsById(Long id);
}
