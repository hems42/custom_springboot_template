package com.org.custom_springboot_template.core.enums;

public enum CoreEnumRoleTypes {

    ADMIN("ADMIN",
            "fully permission",
            "0001"),

    SUPER_USER(
            "SUPER_USER",
            "has permission up user",
            "0002"),

    USER("USER",
            "has user permissions",
            "0003");

    private final String roleName;
    private final String roleDescription;
    private final String roleCode;

    CoreEnumRoleTypes(String exceptionMessage, String exceptionCode, String roleDescription) {
        this.roleName = exceptionMessage;
        this.roleCode = exceptionCode;
        this.roleDescription = roleDescription;
    }


    public String getRoleName() {
        return roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public String getRoleCode() {
        return roleCode;
    }
}