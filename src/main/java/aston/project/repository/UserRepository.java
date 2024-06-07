package aston.project.repository;

import aston.project.model.User;

public interface UserRepository extends Repository<User, Long> {
    boolean exitsById(Long id);
}
