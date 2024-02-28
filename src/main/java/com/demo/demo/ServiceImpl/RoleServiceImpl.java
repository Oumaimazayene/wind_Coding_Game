package com.demo.demo.ServiceImpl;

import com.demo.demo.Repository.RoleRepository;
import com.demo.demo.Service.RoleService;
import com.demo.demo.dtos.RoleDTo;
import com.demo.demo.entity.Answer;
import com.demo.demo.entity.Role;
import com.demo.demo.mappers.RoleMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public List<RoleDTo> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(roleMapper::ToRoleDTo)
                .collect(Collectors.toList());    }

    @Override
    public RoleDTo createRole(RoleDTo roleDTo) {
        return roleMapper.ToRoleDTo(roleRepository.save(roleMapper.ToRole(roleDTo)));

    }

    @Override
    public RoleDTo updateRole(Long id, RoleDTo roleDTo) {
        Optional<Role> existingRole = roleRepository.findById(id);
        if (existingRole.isPresent()) {
            roleMapper.updateRoleFromDTO(roleDTo, existingRole.get());
            return roleMapper.ToRoleDTo(roleRepository.save(existingRole.get()));
        }
        return null;
    }
    @Override
    public void deleteRole(Long id) {
        try {
            roleRepository.deleteById(id);
            System.out.println("Role deleted successfully with ID: " + id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Role with ID " + id + " not found");
            throw e;
        } catch (Exception e) {
            System.err.println("Error deleting Role");
            throw e;
        }
    }

}
