package com.org.custom_springboot_template.product.dto_convertor.log_convertor;

import com.org.custom_springboot_template.entity.log.LogRole;
import com.org.custom_springboot_template.product.dto.LogRoleDto;
import org.springframework.stereotype.Component;

@Component
public class LogRoleDtoConvertor {

    LogRole convert(LogRoleDto from) {
        return new LogRole(
                from.getId(),
                from.getCrud(),
                from.getRole(),
                from.getUser(),
                from.getCreatedDate() == null ? null : from.getCreatedDate()
        );
    }

    LogRoleDto convert(LogRole from) {
        return new LogRoleDto(
                from.getId(),
                from.getTransactionType(),
                from.getRole(),
                from.getUser(),
                from.getCreatedDate() == null ? null : from.getCreatedDate()
        );
    }
}
