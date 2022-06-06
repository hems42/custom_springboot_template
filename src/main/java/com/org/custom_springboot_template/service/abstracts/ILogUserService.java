package com.org.custom_springboot_template.service.abstracts;


import com.org.custom_springboot_template.entity.primary.User;
import com.org.custom_springboot_template.product.request.createRequest.LogUserRequestCreate;

public interface ILogUserService {

    void addLogUser(LogUserRequestCreate requestCreate, User user);



}
