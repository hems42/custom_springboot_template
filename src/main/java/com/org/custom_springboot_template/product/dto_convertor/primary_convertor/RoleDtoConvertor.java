package com.org.custom_springboot_template.product.dto_convertor.primary_convertor;

import com.org.custom_springboot_template.product.dto.RoleDto;
import entity.security.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleDtoConvertor {

    public Role convert(RoleDto from) {
        return new Role(
                from.getRolId(),
                from.getRoleName(),
                from.getRoleDescription(),
                from.getCreatedDate() == null ? null : from.getCreatedDate(),
                from.getUpdatedDate() == null ? null : from.getUpdatedDate()
        );
    }


    public RoleDto convert(Role from) {
        return new RoleDto(
                from.getRolId(),
                from.getRoleName(),
                from.getRoleDescription(),
                from.getCreatedDate() == null ? null : from.getCreatedDate(),
                from.getUpdatedDate() == null ? null : from.getUpdatedDate()
        );
    }


}
