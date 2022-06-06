package com.org.custom_springboot_template.repository;

import com.org.custom_springboot_template.entity.log.LogUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogUserDao extends JpaRepository<LogUser,Integer> {
}
