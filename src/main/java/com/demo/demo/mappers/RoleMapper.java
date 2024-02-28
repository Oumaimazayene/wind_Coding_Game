package com.demo.demo.mappers;

import com.demo.demo.dtos.RoleDTo;
import com.demo.demo.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role ToRole(RoleDTo roleDTo);
    RoleDTo ToRoleDTo(Role role);

    void updateRoleFromDTO(RoleDTo roleDTo, @MappingTarget Role existingRole  );
}
