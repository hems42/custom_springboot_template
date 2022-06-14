package com.org.custom_springboot_template.service.abstracts;

import com.org.custom_springboot_template.entity.primary.User;
import com.org.custom_springboot_template.product.request.createRequest.LogRoleRequestCreate;

public interface ILogRoleService {

    void addLogRole(LogRoleRequestCreate requestCreate, User user);
}
