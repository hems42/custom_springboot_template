package com.org.custom_springboot_template.service.concrete;

import com.org.custom_springboot_template.entity.log.LogRole;
import com.org.custom_springboot_template.entity.primary.User;
import com.org.custom_springboot_template.product.dto_convertor.log_convertor.LogRoleDtoConvertor;
import com.org.custom_springboot_template.product.request.createRequest.LogRoleRequestCreate;
import com.org.custom_springboot_template.repository.LogRoleDao;
import com.org.custom_springboot_template.service.abstracts.ILogRoleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogRoleService implements ILogRoleService {

    private final LogRoleDao roleDao;
    private final LogRoleDtoConvertor roleDtoConvertor;

    public LogRoleService(LogRoleDao roleDao, LogRoleDtoConvertor roleDtoConvertor) {
        this.roleDao = roleDao;
        this.roleDtoConvertor = roleDtoConvertor;
    }

    @Override
    public void addLogRole(LogRoleRequestCreate requestCreate, User user) {

        roleDao.save(new LogRole(
                null,
                requestCreate.getCrud(),
                requestCreate.getRole(),
                user,
                LocalDateTime.now())); }
}
