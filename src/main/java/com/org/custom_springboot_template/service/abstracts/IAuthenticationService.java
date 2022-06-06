package com.org.custom_springboot_template.service.abstracts;

import com.org.custom_springboot_template.product.request.securityRequest.LoginRequest;
import com.org.custom_springboot_template.product.request.securityRequest.SignUpRequest;
import com.org.custom_springboot_template.product.response.*;

public interface IAuthenticationService {

    SignUpResponse signUp(SignUpRequest signupRequest);

    RegistrationResponse register(String confirmationToken);

    LoginResponse logIn(LoginRequest loginRequest);

    RefreshTokenResponse refreshToken(String refreshToken);

    LogOutResponse logOut(String refreshToken);

    void forgetPassword();

    void reSendEmail();

    String deneme(String deneme);
}
