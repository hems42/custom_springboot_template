package com.org.custom_springboot_template.repository;

import com.org.custom_springboot_template.entity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface RoleDao extends JpaRepository<Role, Integer> {

    @Query("SELECT r FROM Role r WHERE r.roleName = ?1")
    Role getRoleByRoleName(String roleName);

}
