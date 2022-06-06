package com.org.custom_springboot_template.service.abstracts;

import com.org.custom_springboot_template.entity.primary.User;
import com.org.custom_springboot_template.entity.security.ConfirmationToken;

import java.util.Optional;

public interface IConfirmationTokenService {

    ConfirmationToken createConfirmationToken(User user);

    Optional<ConfirmationToken> saveConfirmationToken(ConfirmationToken token);

    Optional<ConfirmationToken> getConfirmationToken(String token);

    Optional<ConfirmationToken> getConfirmationToken(User user);

    Boolean setConfirmedAt(String token);

    Boolean deleteConfirmationToken(ConfirmationToken confirmationToken);

    Boolean deleteConfirmationToken(Integer tokenId);

    Boolean deleteConfirmationToken(String  confirmationToken);

    Boolean deleteConfirmationToken(User user);

    Boolean verifyConfirmationToken(String confirmationToken);
}
