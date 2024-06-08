package aston.project.service.impl;

import aston.project.servlet.dto.RoleIncomingDto;
import aston.project.servlet.dto.RoleOutGoingDto;
import aston.project.servlet.dto.RoleUpdateDto;
import aston.project.servlet.mapper.RoleDtoMapper;
import aston.project.model.Role;
import aston.project.service.RoleService;
import aston.project.exception.NotFoundException;
import aston.project.servlet.mapper.impl.RoleDtoMapperImpl;
import aston.project.repository.RoleRepository;
import aston.project.repository.impl.RoleRepositoryImpl;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository = RoleRepositoryImpl.getInstance();
    private static RoleService instance;
    private final RoleDtoMapper roleDtoMapper = RoleDtoMapperImpl.getInstance();


    private RoleServiceImpl() {
    }

    public static synchronized RoleService getInstance() {
        if (instance == null) {
            instance = new RoleServiceImpl();
        }
        return instance;
    }

    @Override
    public RoleOutGoingDto save(RoleIncomingDto roleDto) {
        Role role = roleDtoMapper.map(roleDto);
        role = roleRepository.save(role);
        return roleDtoMapper.map(role);
    }

    @Override
    public void update(RoleUpdateDto roleUpdateDto) throws NotFoundException {
        checkRoleExist(roleUpdateDto.getId());
        Role role = roleDtoMapper.map(roleUpdateDto);
        roleRepository.update(role);
    }

    @Override
    public RoleOutGoingDto findById(Long roleId) throws NotFoundException {
        Role role = roleRepository.findById(roleId).orElseThrow(() ->
                new NotFoundException("Role not found."));
        return roleDtoMapper.map(role);
    }

    @Override
    public List<RoleOutGoingDto> findAll() {
        List<Role> roleList = roleRepository.findAll();
        return roleDtoMapper.map(roleList);
    }

    @Override
    public boolean delete(Long roleId) throws NotFoundException {
        checkRoleExist(roleId);
        return roleRepository.deleteById(roleId);
    }

    private void checkRoleExist(Long roleId) throws NotFoundException {
        if (!roleRepository.exitsById(roleId)) {
            throw new NotFoundException("Role not found.");
        }
    }
}
