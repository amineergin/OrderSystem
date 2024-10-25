package com.example.qkare.CustomerOrders.Service.Impl;

import com.example.qkare.CustomerOrders.Exception.Exceptions;
import com.example.qkare.CustomerOrders.Exception.MessageKey;
import com.example.qkare.CustomerOrders.Model.Entity.Role;
import com.example.qkare.CustomerOrders.Repository.RoleRepository;
import com.example.qkare.CustomerOrders.Service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        if(roleRepository.existsById(role.getId())){
            throw new Exceptions(MessageKey.ROLE_ALREADY_EXISTS, role.getName());
        }
        return roleRepository.save(role);
    }
}
