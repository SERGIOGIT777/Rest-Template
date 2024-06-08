package aston.project.service;

import aston.project.servlet.dto.RoleIncomingDto;
import aston.project.servlet.dto.RoleOutGoingDto;
import aston.project.servlet.dto.RoleUpdateDto;
import aston.project.exception.NotFoundException;

import java.util.List;

public interface RoleService {
    RoleOutGoingDto save(RoleIncomingDto role);

    void update(RoleUpdateDto role) throws NotFoundException;

    RoleOutGoingDto findById(Long roleId) throws NotFoundException;

    List<RoleOutGoingDto> findAll();

    boolean delete(Long roleId) throws NotFoundException;
}
