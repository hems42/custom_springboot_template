package com.org.custom_springboot_template.core.constants;

public class CoreConstantExceptionErrorCodes {

    /*
    
    
    CUSTOM EXCEPTION CODES:
    
   //******************************************************************
    
    Error_MainGroupCodeList :
    
    - UN                      : 10
    - ALREADY                 : 20
    - NOT                     : 30
    - NON                     : 40
    - FORBIDDEN               : 50
    
    
    
    
   //*****************************************************************
    
    Error_SubGroupCodeList :
    
     - EXIST                  : 10
     - VALID                  : 20
     - FOUND                  : 30
     - SUCCESS                : 40
     - ACCEPT                 : 50
     - AUTHORIZE              : 60
     
	
   //******************************************************************
    
    Error_EntityCodeList :
    
      - T_USER                  : 010
      - T_ROLE                  : 020
      - T_REQUEST               : 030
      - REFRESH TOKEN         : 040
      - CONFIRMATION TOKEN    : 050
      - ACCESS TOKEN          : 060
      - T_REGISTRATION          : 070
      - T_LOGIN                 : 080
      - T_EMAIL                 : 090 
      - T_SIGNUP                : 100
      - T_LOGIN                 : 101
      - T_LOGOUT                : 102
           
     
   //******************************************************************
    
    Error_EventCodeList :
        
     
    
     */
	
	
	//-Error_MainGroupCodeList :
	
	private final static String UN                            = "10";
	private final static String ALREADY                       = "20";
	private final static String NOT                           = "30";
	private final static String NON                           = "40";
	private final static String FORBIDDEN                     = "50";
	
	
	//-Error_SubGroupCodeList :
	
	private final static String EXIST                          = "10";
    private final static String VALID                          = "20";
    private final static String FOUND                          = "30";
    private final static String SUCCESS                        = "40";
    private final static String ACCEPT                         = "50";
    private final static String AUTHORIZE                      = "60";

    
    
    //-Error_EntityCodeList :
    
    public final static String T_USER                            = "010";
    public final static String T_ROLE                            = "020";
    public final static String T_REQUEST                         = "030";
    public final static String T_REFRESH_TOKEN                   = "040";
    public final static String T_CONFIRMATION_TOKEN              = "050";
    public final static String T_ACCESS_TOKEN                    = "060";
    public final static String T_REGISTRATION                    = "070";
    public final static String T_EMAIL                           = "080";
    public final static String T_SIGNUP                          = "090";
    public final static String T_LOGIN                           = "091";
    public final static String T_LOGOUT                          = "092";
    
    
     
    //-Error_ExceptionMessageCodeList :
    
    public static String B_NOT_FOUND_EXCEPTION_ERROR_CODE                      = NOT + FOUND;
    public static String B_ALREADY_EXIST_EXCEPTION_ERROR_CODE                  = ALREADY + EXIST;
    public static String B_ALREADY_ACCEPTED_EXCEPTION_ERROR_CODE               = ALREADY + ACCEPT;
    public static String B_FORBIDDEN_EXCEPTION_ERROR_CODE                      = FORBIDDEN + "00";
    public static String B_UN_AUTHORIZE_EXCEPTION_ERROR_CODE                   = UN + AUTHORIZE;
    public static String B_UN_SUCCESSFUL_EXCEPTION_ERROR_CODE                  = UN + SUCCESS;
    public static String B_UN_ACCEPTABLE_EXCEPTION_ERROR_CODE                  = UN + ACCEPT;
    public static String B_NOT_VALID_EXCEPTION_ERROR_CODE                      = NOT + VALID;

    
    
    //-Error_EventCodeList :

    public static String D_EMAIL_ALREADY_USED                                  = "0001";
    public static String D_USERNAME_ALREADY_USED                               = "0002";
    public static String D_EMAIL_AND_USERNAME_ALREADY_USED                     = "0003";
    
    // T_USER
    public static String D_USER_NOT_CREATED                                    = "0010"; 
    public static String D_USER_ALREADY_REGISTERED                             = "0011";
    public static String D_USER_NOT_REGISTERED                                 = "0012";
    public static String D_USER_NOT_FOUND                                      = "0013";
    public static String D_USER_NOT_ACTIVE                                     = "0014";
    public static String D_USER_ALREADY_LOGIN                                  = "0015";
    
    
    // AUTHENTICATION
    public static String D_ACCESS_TOKEN_NOT_CREATED_OR_SAVED                   = "0020";
    public static String D_ACCESS_TOKEN_STILL_VALID                            = "0021";
    public static String D_REFRESH_TOKEN_NOT_CREATED_OR_SAVED                  = "0024";
    public static String D_REFRESH_TOKEN_NOT_FOUND                             = "0025";
    public static String D_REFRESH_TOKEN_NOT_VALID                             = "0026";
    public static String D_CONFIRMATION_TOKEN_NOT_CREATED_OR_SAVED             = "0030";
    public static String D_CONFIRMATION_TOKEN_NOT_FOUND                        = "0031";
    public static String D_CONFIRMATION_TOKEN_EXPIRED                          = "0032";
    public static String D_CONFIRMATION_TOKEN_ALREADY_USED                     = "0033";
    public static String D_CONFIRMATION_TOKEN_NOT_REGISTERED                   = "0034";

}
