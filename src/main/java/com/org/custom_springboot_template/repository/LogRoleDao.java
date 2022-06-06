package com.org.custom_springboot_template.repository;

import com.org.custom_springboot_template.entity.log.LogRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRoleDao extends JpaRepository<LogRole,Integer> {
}
