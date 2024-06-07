package aston.project.mapper;

import aston.project.dto.RoleIncomingDto;
import aston.project.dto.RoleOutGoingDto;
import aston.project.dto.RoleUpdateDto;
import aston.project.model.Role;

import java.util.List;

public interface RoleDtoMapper {
    Role map(RoleIncomingDto roleIncomingDto);

    Role map(RoleUpdateDto roleUpdateDto);

    RoleOutGoingDto map(Role role);

    List<RoleOutGoingDto> map(List<Role> roleList);
}
