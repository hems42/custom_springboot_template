package com.org.custom_springboot_template.service.concrete;

import com.org.custom_springboot_template.entity.log.LogUser;
import com.org.custom_springboot_template.entity.primary.User;
import com.org.custom_springboot_template.product.dto_convertor.log_convertor.LogUserDtoConvertor;
import com.org.custom_springboot_template.product.request.createRequest.LogUserRequestCreate;
import com.org.custom_springboot_template.repository.LogUserDao;
import com.org.custom_springboot_template.service.abstracts.ILogUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogUserService implements ILogUserService {

    private final LogUserDtoConvertor logUserDtoConvertor;
    private final LogUserDao logUserDao;


    public LogUserService(LogUserDtoConvertor logUserDtoConvertor, LogUserDao logUserDao) {
        this.logUserDtoConvertor = logUserDtoConvertor;
        this.logUserDao = logUserDao;
    }

    @Override
    public void addLogUser(LogUserRequestCreate requestCreate, User user) {
        logUserDao.save(new LogUser(
                null,
                requestCreate.getUserOperationType(),
                user, LocalDateTime.now())); } }
