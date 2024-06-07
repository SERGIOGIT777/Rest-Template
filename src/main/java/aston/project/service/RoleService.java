package aston.project.service;

import aston.project.dto.RoleIncomingDto;
import aston.project.dto.RoleOutGoingDto;
import aston.project.dto.RoleUpdateDto;
import aston.project.exception.NotFoundException;

import java.util.List;

public interface RoleService {
    RoleOutGoingDto save(RoleIncomingDto role);

    void update(RoleUpdateDto role) throws NotFoundException;

    RoleOutGoingDto findById(Long roleId) throws NotFoundException;

    List<RoleOutGoingDto> findAll();

    boolean delete(Long roleId) throws NotFoundException;
}
