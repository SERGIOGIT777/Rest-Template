package aston.project.servlet.mapper.impl;

import aston.project.servlet.dto.RoleIncomingDto;
import aston.project.servlet.dto.RoleOutGoingDto;
import aston.project.servlet.dto.RoleUpdateDto;
import aston.project.servlet.mapper.RoleDtoMapper;
import aston.project.model.Role;

import java.util.List;

public class RoleDtoMapperImpl implements RoleDtoMapper {
    private static RoleDtoMapper instance;

    private RoleDtoMapperImpl() {
    }

    public static synchronized RoleDtoMapper getInstance() {
        if (instance == null) {
            instance = new RoleDtoMapperImpl();
        }
        return instance;
    }

    @Override
    public Role map(RoleIncomingDto roleIncomingDto) {
        return new Role(
                null,
                roleIncomingDto.getName()
        );
    }

    @Override
    public Role map(RoleUpdateDto roleUpdateDto) {
        return new Role(
                roleUpdateDto.getId(),
                roleUpdateDto.getName());
    }

    @Override
    public RoleOutGoingDto map(Role role) {
        return new RoleOutGoingDto(
                role.getId(),
                role.getName()
        );
    }

    @Override
    public List<RoleOutGoingDto> map(List<Role> roleList) {
        return roleList.stream().map(this::map).toList();
    }
}