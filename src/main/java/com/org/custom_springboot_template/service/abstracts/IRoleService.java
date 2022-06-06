package com.org.custom_springboot_template.service.abstracts;

import com.org.custom_springboot_template.core.enums.CoreEnumRoleTypes;
import com.org.custom_springboot_template.core.utilities.results.DataResult;
import com.org.custom_springboot_template.entity.primary.User;
import com.org.custom_springboot_template.entity.security.Role;
import com.org.custom_springboot_template.product.dto.RoleDto;
import com.org.custom_springboot_template.product.request.createRequest.RoleRequestCreate;
import com.org.custom_springboot_template.product.request.updateRequest.RoleRequestUpdate;

import java.util.List;

public interface IRoleService {

    DataResult<RoleDto> addRole(RoleRequestCreate requestCreate, User user);

    DataResult<RoleDto> addRole(RoleRequestCreate requestCreate);

    DataResult<List<RoleDto>> getAllRole();

    DataResult<RoleDto> updateRole(RoleRequestUpdate requestUpdate);

    DataResult<Role> getRoleByRoleName(CoreEnumRoleTypes roleType);
}
