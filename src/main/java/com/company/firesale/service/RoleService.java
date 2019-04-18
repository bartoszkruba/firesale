package com.company.firesale.service;

import com.company.firesale.data.entity.Role;
import com.company.firesale.data.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void addRole(Role role) {
        roleRepository.save(role);
    }

    public Role findByDescrption(String description) {
        return roleRepository.findDistinctByDescription(description);
    }
}
