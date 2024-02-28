package com.demo.demo.Service;

import com.demo.demo.dtos.RoleDTo;
import com.demo.demo.dtos.TypeDto;
import com.demo.demo.entity.Role;
import com.demo.demo.entity.Type;

import java.util.List;

public interface RoleService {
    Role getRoleById(Long id);
    List<RoleDTo> getAllRoles();
    RoleDTo createRole(RoleDTo roleDTo);
    RoleDTo updateRole(Long id, RoleDTo roleDTo);
    void deleteRole(Long id);

}
