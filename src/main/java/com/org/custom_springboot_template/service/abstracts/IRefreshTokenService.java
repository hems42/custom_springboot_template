package com.org.custom_springboot_template.service.abstracts;

import com.org.custom_springboot_template.entity.primary.User;
import com.org.custom_springboot_template.entity.security.RefreshToken;

import java.util.Optional;

public interface IRefreshTokenService {

    RefreshToken createRefreshToken(User user);

    Optional<RefreshToken> saveRefreshToken(RefreshToken refreshToken);

    Optional<RefreshToken> getRefreshTokenByToken(String refreshToken);

    Optional<RefreshToken> getRefreshTokenByUser(User user);

    Optional<User> getUserByRefreshToken(RefreshToken refreshToken);

    Boolean verifyRefreshToken(RefreshToken refreshToken);

    Boolean verifyRefreshToken(User user);

    Boolean deleteRefreshToken(RefreshToken refreshToken);

    Boolean deleteRefreshToken(User user);

}
