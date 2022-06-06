package com.org.custom_springboot_template.core.enums;

import static com.org.custom_springboot_template.core.constants.CoreConstantExceptionErrorCodes.*;

public enum CoreEnumExceptionMessages {


    //AUTHENTICATION***

    // ACCESS TOKEN
    NOT_VALID_ACCESS_TOKEN("ACCESS_TOKEN_EXPIRED",T_ACCESS_TOKEN),

    //T_LOGIN
    UN_SUCCESSFUL_RENEW_REFRESH_TOKEN("UN_SUCCESSFUL_RENEW_REFRESH_TOKEN",T_REFRESH_TOKEN),

    //T_REGISTRATION
    UN_SUCCESSFUL_REGISTRATION("UN_SUCCESSFUL_REGISTRATION",T_REGISTRATION),


    //T_SIGNUP
    UN_SUCCESSFUL_SIGNUP("UN_SUCCESSFUL_SIGNUP",T_SIGNUP),

    //T_LOGIN
    UN_SUCCESSFUL_LOGIN("UN_SUCCESSFUL_LOGIN",T_LOGIN);








    private final String exceptionMessage;
    private final String exceptionCode;

    CoreEnumExceptionMessages(String exceptionMessage, String exceptionCode) {
        this.exceptionMessage = exceptionMessage;
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

}
