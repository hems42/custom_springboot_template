package com.org.custom_springboot_template.service.abstracts;

import com.org.custom_springboot_template.product.response.AccessTokenResponse;
import org.springframework.security.core.Authentication;

public interface IAccessTokenService {

    Authentication authenticateUser(String userName, String password);

    AccessTokenResponse createAccessTokenWithUserName(String userNickName);

    Boolean verifyAccessToken(String accessToken);

    String getUserNameFromAccessToken(String accessToken);

    String getAccessTokenFromUserName(String userName);

    Boolean isNotExpired(String accessToken);

}
