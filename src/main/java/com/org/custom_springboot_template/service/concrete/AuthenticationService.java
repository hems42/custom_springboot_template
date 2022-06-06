package com.org.custom_springboot_template.service.concrete;

import com.org.custom_springboot_template.product.dto_convertor.primary_convertor.UserDtoConvertor;
import com.org.custom_springboot_template.product.request.securityRequest.LoginRequest;
import com.org.custom_springboot_template.product.request.securityRequest.SignUpRequest;
import com.org.custom_springboot_template.product.response.*;
import com.org.custom_springboot_template.service.abstracts.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class AuthenticationService implements IAuthenticationService {

    private final IUserService userService;
    private final UserDtoConvertor userDtoConvertor;
    private final IRegistrationService registrationService;
    private final IAccessTokenService accessTokenService;
    private final IRefreshTokenService refreshTokenService;
    private final IConfirmationTokenService confirmationTokenService;

    private final String logTitle = "AuthenticationService : -> ";


    public AuthenticationService(IUserService userService,
                                 UserDtoConvertor userDtoConvertor,
                                 IRegistrationService registrationService,
                                 IAccessTokenService accessTokenService,
                                 IRefreshTokenService refreshTokenService,
                                 IConfirmationTokenService confirmationTokenService) {
        this.userService = userService;
        this.userDtoConvertor = userDtoConvertor;
        this.registrationService = registrationService;
        this.accessTokenService = accessTokenService;
        this.refreshTokenService = refreshTokenService;
        this.confirmationTokenService = confirmationTokenService;
    }

    @Override
    public SignUpResponse signUp(SignUpRequest signupRequest) {
        return null;
    }

    @Override
    public RegistrationResponse register(String confirmationToken) {
        return null;
    }

    @Override
    public LoginResponse logIn(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public RefreshTokenResponse refreshToken(String refreshToken) {
        return null;
    }

    @Override
    public LogOutResponse logOut(String refreshToken) {
        return null;
    }

    @Override
    public void forgetPassword() {

    }

    @Override
    public void reSendEmail() {

    }

    @Override
    public String deneme(String deneme) {
        return null;
    }



/*
    @Override
    public SignUpResponse signUp(SignUpRequest signupRequest) {

        UserDto userDtoCreated;
        User userCreated;
        RefreshToken refreshTokenCreated;

        try {
            userDtoCreated = userService.addUser(
                    new UserRequestCreate(signupRequest.getUsername(),
                            signupRequest.getEmail(), signupRequest.getPassword()));
            userCreated = userDtoConvertor.convert(userDtoCreated);

            AccessTokenResponse accessTokenResponse = accessTokenService.createAccessTokenWithUserName(
                    signupRequest.getUsername());

            accessTokenService.authenticateUser(signupRequest.getUsername(),
                    signupRequest.getPassword());

            refreshTokenCreated = refreshTokenService.createRefreshToken(userCreated);
            refreshTokenService.saveRefreshToken(refreshTokenCreated);

            new Thread(() -> registrationService
                    .register(
                            new RegistrationRequest(
                                    signupRequest.getUsername(),
                                    signupRequest.getEmail(),
                                    signupRequest.getPassword()),
                            userCreated
                    )).start();

            log.info(logTitle + "user created successfully");

            return new SignUpResponse(
                    accessTokenResponse.getAccessToken(),
                    refreshTokenCreated.getRefreshToken(),
                    userDtoCreated.getId(),
                    userDtoCreated.getUsername(),
                    userDtoCreated.getEmail(),
                    userDtoCreated.getActive(),
                    userDtoCreated.getRegistered());
        } catch (BaseExceptionModel exceptionModel) {
            log.error(logTitle + "user not not created: " + exceptionModel.getErrorDescription());

            throw new UnSuccessfulException(UN_SUCCESSFUL_USER_CREATE,
                    exceptionModel.getErrorCode() + " : " + exceptionModel.getErrorDescription());
        }

    }


    @Override
    public RegistrationResponse register(String confirmationToken) {

        ConfirmationToken confirmationTokenFound = null;

        try {
            confirmationTokenFound = confirmationTokenService.getConfirmationToken(confirmationToken).get();

            if (confirmationTokenFound != null) {

                log.info(logTitle + "confirmation token found by confirmation token");

                Boolean verifyResult = confirmationTokenService.verifyConfirmationToken(confirmationToken);

                if (verifyResult) {

                    log.info(logTitle + "confirmation token verified");

                    if (confirmationTokenService.setConfirmedAt(confirmationTokenFound.getConfirmationToken())) {

                        log.info(logTitle + "confirmation token confirmed successfully");

                        userService.setConfirmedUser(confirmationTokenFound.getUser());

                        log.info(logTitle + " user confirmed");

                        log.info(logTitle + "added to log user registered");

                        return new RegistrationResponse(
                                "user registered",
                                confirmationTokenFound.getUser().getEmail(),
                                confirmationTokenFound.getUser().getNickName()
                        );
                    } else {

                        log.error(logTitle + " confirmation token could not be confirmed");

                        throw new UnSuccessfulException(UN_SUCCESSFUL_REGISTRATION, "token confirmation unsuccess");
                    }


                } else {

                    log.error(logTitle + "confirmation token not verified");

                    throw new UnSuccessfulException(UN_SUCCESSFUL_REGISTRATION, "not verified confirmation token");
                }

            } else {

                log.error(logTitle + "confirmation token not found");

                throw new NotFoundException(NOT_FOUND_CONFIRMATION_TOKEN, "not found confirmation token");
            }
        } catch (BaseExceptionModel exceptionModel) {

            log.error(logTitle + "user not confirmed because : " + exceptionModel.getErrorDescription());

            throw new UnSuccessfulException(UN_SUCCESSFUL_REGISTRATION,
                    exceptionModel.getErrorCode() + " : " + exceptionModel.getErrorDescription());
        }
    }


    @Override
    public LoginResponse logIn(LoginRequest loginRequest) {

        UserDto userDtoFound = null;
        User userFound = null;
        String _accessToken;
        String _refreshToken;

        try {
            if (loginRequest.getPassword() != null) {
                if (loginRequest.getUserNickname() != null && loginRequest.getEmail() != null) {

                    log.error(logTitle + "just one of username or email options");
                    throw new UnAcceptableException(UN_ACCEPTABLE_LOGIN_REQUEST,
                            "just one from username or email options");

                } else if (loginRequest.getUserNickname() != null && loginRequest.getEmail() == null) {

                    userDtoFound = userService
                            .getUserByNickName(loginRequest.getUserNickname());

                    userFound = userDtoConvertor
                            .convert(userDtoFound);

                } else if (loginRequest.getUserNickname() == null && loginRequest.getEmail() != null) {
                    userDtoFound = userService
                            .getUserByEmail(loginRequest.getEmail());

                    userFound = userDtoConvertor
                            .convert(userDtoFound);
                } else if (loginRequest.getUserNickname() == null && loginRequest.getEmail() == null) {

                    log.error(logTitle + "need email or username to login");
                    throw new UnAcceptableException(UN_ACCEPTABLE_LOGIN_REQUEST,
                            "need email or username to login");
                }
            } else {
                log.error(logTitle + "need password to login");
                throw new UnAcceptableException(UN_ACCEPTABLE_LOGIN_REQUEST,
                        "need password to login");
            }


            if (userDtoFound != null) {

                if (userService.verifyUserPassword(userFound, loginRequest.getPassword())) {
                    if (!refreshTokenService.getRefreshTokenByUser(userFound).isPresent()) {

                        _accessToken = accessTokenService
                                .createAccessTokenWithUserName(userDtoFound.getNickName())
                                .getAccessToken();

                        accessTokenService
                                .authenticateUser(
                                        userDtoFound.getNickName(),
                                        loginRequest.getPassword()
                                );

                        _refreshToken = refreshTokenService
                                .saveRefreshToken(refreshTokenService
                                        .createRefreshToken(userFound))
                                .get().getRefreshToken();

                        userService.addLogInLogToUser(null, userFound);

                        return new LoginResponse(userDtoFound.getId(),
                                userDtoFound.getNickName(),
                                userDtoFound.getEmail(),
                                userDtoFound.getActive(),
                                userDtoFound.getRegistered(),
                                userDtoFound.getRoles(),
                                userDtoFound.getCreatedDate(),
                                userDtoFound.getUpdatedDate(),
                                _accessToken,
                                _refreshToken);

                    } else {
                        log.error(logTitle + "user already login");
                        throw new AlReadyExistException(ALREADY_EXIST_REFRESH_TOKEN, "user already login");
                    }
                } else {

                    log.error(logTitle + "user password not matched with sent password");

                    throw new UnAcceptableException(UN_ACCEPTABLE_USER_PASSWORD, "user password not matched with sent password");
                }

            } else {
                log.error(logTitle + "user not found for login");
                throw new NotFoundException(NOT_FOUND_USER, "user not found for login");
            }
        } catch (BaseExceptionModel exceptionModel) {
            log.error(logTitle + "user not login because : " + exceptionModel.getErrorDescription());

            throw new UnSuccessfulException(UN_SUCCESSFUL_LOGIN,
                    exceptionModel.getErrorCode() + " : " + exceptionModel.getErrorDescription());
        }
    }

    @Override
    public RefreshTokenResponse refreshToken(String refreshToken) {

        RefreshToken refreshTokenFound;
        User userFound;
        Optional optionalRefreshToken = refreshTokenService.getRefreshTokenByToken(refreshToken);

        if (optionalRefreshToken.isPresent()) {
            refreshTokenFound = (RefreshToken) optionalRefreshToken.get();
            userFound = refreshTokenFound.getUser();
            if (refreshTokenService.verifyRefreshToken(refreshTokenFound)) {

                String accessTokenRenew = accessTokenService
                        .createAccessTokenWithUserName(userFound.getNickName())
                        .getAccessToken();
                accessTokenService.verifyAccessToken(accessTokenRenew);

                return new RefreshTokenResponse(
                        refreshTokenFound.getRefreshToken(),
                        accessTokenRenew,
                        true,
                        true);
            } else {

                refreshTokenService.deleteRefreshToken(refreshTokenFound);
                throw new UnSuccessfulException(UN_SUCCESSFUL_REFRESH_TOKEN_RENEW, "refresh token is not valid");

            }
        } else {
            throw new UnSuccessfulException(UN_SUCCESSFUL_REFRESH_TOKEN_RENEW, "refresh token not found");
        }
    }

    @Override
    public LogOutResponse logOut(String refreshToken) {

        RefreshToken refreshTokenFound = null;

        try {

            refreshTokenFound = refreshTokenService.getRefreshTokenByToken(refreshToken).get();

            refreshTokenService.deleteRefreshToken(refreshTokenFound);

            userService.addLogOutLogToUser(null, refreshTokenFound.getUser());

            return new LogOutResponse(true, LocalDateTime.now(), refreshToken);


        } catch (BaseExceptionModel exceptionModel) {

            if (exceptionModel.getErrorCode().startsWith(NOT_FOUND_EXCEPTION_ERROR_CODE)) {

            } else if (exceptionModel.getErrorCode().startsWith(ALREADY_EXIST_EXCEPTION_ERROR_CODE)) {
                refreshTokenService.deleteRefreshToken(refreshTokenFound);

                userService.addLogOutLogToUser(null, refreshTokenFound.getUser());

                return new LogOutResponse(true, LocalDateTime.now(), refreshToken);
            }

            log.error(logTitle + "user did not logout because : " + exceptionModel.getErrorDescription());

            throw new UnSuccessfulException(UN_SUCCESSFUL_LOGOUT,
                    exceptionModel.getErrorCode() + " : " + exceptionModel.getErrorDescription());
        }

    }

    @Override
    public void forgetPassword() {

    }

    public void reSendEmail() {
    }

    @Override
    public String deneme(String deneme) {

        if (deneme.isEmpty()) {
            throw new UnAcceptableException(UN_ACCEPTABLE_ACCESS_TOKEN, "denem hata 1");
        } else if (deneme.startsWith("A")) {
            throw new NotFoundException(NOT_FOUND_ACCESS_TOKEN, "deneme hata 2");
        } else {
            return "deneme ile sonuç gönderildi...";
        }
    }*/
}
