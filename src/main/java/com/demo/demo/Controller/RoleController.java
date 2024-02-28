package com.demo.demo.Controller;

import com.demo.demo.Service.RoleService;
import com.demo.demo.dtos.AnswerDTo;
import com.demo.demo.dtos.RoleDTo;
import com.demo.demo.entity.Answer;
import com.demo.demo.entity.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }
    @GetMapping("/roles")
    public List<RoleDTo> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping("/add")
    public ResponseEntity<?> createRole(@RequestBody RoleDTo roleDTo) {
        return ResponseEntity.ok().body(roleService.createRole(roleDTo));
    }
    @PutMapping("/{id}")

    public RoleDTo updateRole(@PathVariable Long id, @RequestBody RoleDTo roleDTo) {
        return roleService.updateRole(id, roleDTo);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService
                .deleteRole(id);
    }
}
