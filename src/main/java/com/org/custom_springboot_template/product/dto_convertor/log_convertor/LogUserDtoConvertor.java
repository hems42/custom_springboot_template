package com.org.custom_springboot_template.product.dto_convertor.log_convertor;

import com.org.custom_springboot_template.entity.log.LogUser;
import com.org.custom_springboot_template.product.dto.LogUserDto;
import entity.log.LogUser;
import org.springframework.stereotype.Component;

@Component
public class LogUserDtoConvertor {

    public LogUser convert(LogUserDto from) {
        return new LogUser(
                from.getId(),
                from.getUserOperationType(),
                from.getUser(),
                from.getCreatedDate() == null ? null : from.getCreatedDate()
        );
    }

    public LogUserDto convert(LogUser from) {
        return new LogUserDto(
                from.getId(),
                from.getTransactionType(),
                from.getUser(),
                from.getCreatedDate() == null ? null : from.getCreatedDate()
        );
    }
}
