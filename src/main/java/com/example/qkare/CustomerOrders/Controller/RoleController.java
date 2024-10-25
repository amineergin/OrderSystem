package com.example.qkare.CustomerOrders.Controller;

import com.example.qkare.CustomerOrders.Model.Entity.Role;
import com.example.qkare.CustomerOrders.Service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/save")
    ResponseEntity<Role> save(@RequestBody Role role) {
        Role newRole = roleService.save(role);
        return ResponseEntity.ok(newRole);
    }
}
