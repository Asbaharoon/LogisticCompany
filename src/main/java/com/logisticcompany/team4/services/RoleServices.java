package com.logisticcompany.team4.services;

import com.logisticcompany.team4.model.Role;
import com.logisticcompany.team4.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class RoleServices {

	@Autowired
    private RoleRepository roleRepository;
    
    public List<Role> findAll() {
        List<Role> roles = roleRepository.findAll();
        return roles;
    }

    public void addRole(@ModelAttribute Role role) {
        roleRepository.save(role);
    }

    public Role findRoleById(@PathVariable("id") int id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid role Id:" + id));
        return role;
    }

    public void updateRole(@ModelAttribute Role role) throws Exception {
        Role roleInDB = roleRepository.findById(role.getId()).orElse(null);
        if (roleInDB != null) {
            roleInDB.setName(role.getName());
            roleInDB.setRoleType(role.getRoleType());
            roleRepository.save(roleInDB);
        } else {
            throw new Exception("Role not found");
        }
    }

    public void deleteRole(@PathVariable("id") int id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role Id:" + id));
        roleRepository.delete(role);
    }
}
