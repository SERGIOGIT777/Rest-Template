package aston.project.servlet.mapper;

import aston.project.servlet.dto.RoleIncomingDto;
import aston.project.servlet.dto.RoleOutGoingDto;
import aston.project.servlet.dto.RoleUpdateDto;
import aston.project.model.Role;

import java.util.List;

public interface RoleDtoMapper {
    Role map(RoleIncomingDto roleIncomingDto);

    Role map(RoleUpdateDto roleUpdateDto);

    RoleOutGoingDto map(Role role);

    List<RoleOutGoingDto> map(List<Role> roleList);
}
