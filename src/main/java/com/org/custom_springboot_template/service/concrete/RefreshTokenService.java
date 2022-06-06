package com.org.custom_springboot_template.service.concrete;

import com.org.custom_springboot_template.core.constants.CoreConstantExceptionErrorCodes;
import com.org.custom_springboot_template.entity.primary.User;
import com.org.custom_springboot_template.entity.security.RefreshToken;
import com.org.custom_springboot_template.repository.RefreshTokenDao;
import com.org.custom_springboot_template.service.abstracts.IRefreshTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class RefreshTokenService implements IRefreshTokenService {

    private final RefreshTokenDao refreshTokenRepository;

    public RefreshTokenService(RefreshTokenDao refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public RefreshToken createRefreshToken(User user) {

        return new RefreshToken(
                null,
                user,
                UUID.randomUUID().toString(),
                Instant.now().plusMillis(CoreConstantExceptionErrorCodes.REFRESH_TOKEN_EXPERIMENT_TIME),
                LocalDateTime.now()
        );
    }

    @Override
    public Optional<RefreshToken> saveRefreshToken(RefreshToken refreshToken) {
            return Optional.of(refreshTokenRepository.save(refreshToken));
    }

    @Override
    public Optional<RefreshToken> getRefreshTokenByToken(String refreshToken) {
        RefreshToken refreshTokenFound = refreshTokenRepository.getByRefreshToken(refreshToken);

        if (refreshTokenFound != null) {
            return Optional.of(refreshTokenFound);
        } else {

            log.error("not found refresh token by refresh token");
            return Optional.empty();
        }

    }

    @Override
    public Optional<RefreshToken> getRefreshTokenByUser(User user) {

        if(refreshTokenRepository.getRefreshTokenByUser(user)!=null)
        {
            return Optional.of(refreshTokenRepository.getRefreshTokenByUser(user));
        } else {
          return  Optional.empty();
        }
    }

    @Override
    public Optional<User> getUserByRefreshToken(RefreshToken refreshToken) {

        return Optional.empty();
    }

    @Override
    public Boolean verifyRefreshToken(RefreshToken refreshToken) {

        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {

            refreshTokenRepository.delete(refreshToken);

            log.warn("refresh token expired and deleted");

           return false;

        } else {
            return true;
        }
    }

    @Override
    public Boolean verifyRefreshToken(User user) {
        RefreshToken refreshTokenFound = util_getRefreshToken(user);
        return util_verifyRefreshToken(refreshTokenFound);
    }

    @Override
    public Boolean deleteRefreshToken(RefreshToken refreshToken) {
            return refreshTokenRepository.deleteByRefreshToken(refreshToken) > 0;
    }

    @Override
    public Boolean deleteRefreshToken(User user) {
            return refreshTokenRepository.deleteByUser(user) > 0;
    }


    //***********************************************//

    //UTIL

    private RefreshToken util_getRefreshToken(Integer id) {

        RefreshToken refreshTokenFound = refreshTokenRepository.getById(id);

        if (refreshTokenFound != null) {
            return refreshTokenFound;
        } else {

            log.error("not found refresh token by ıd");

           throw new NotFoundException(NOT_FOUND_REFRESH_TOKEN, "not found refresh token by ıd");
        }
    }

    private RefreshToken util_getRefreshToken(User user) {

        RefreshToken refreshTokenFound = refreshTokenRepository.getRefreshTokenByUser(user);

        if (refreshTokenFound != null) {
            return refreshTokenFound;
        } else {

            log.error("not found refresh token by user");

             throw new NotFoundException(NOT_FOUND_REFRESH_TOKEN, "not found refresh token by user");
        }
    }

    private RefreshToken util_getRefreshToken(String refreshToken) {

        RefreshToken refreshTokenFound = refreshTokenRepository.getByRefreshToken(refreshToken);

        if (refreshTokenFound != null) {
            return refreshTokenFound;
        } else {

            log.error("not found refresh token by refresh token");

            throw new NotFoundException(NOT_FOUND_REFRESH_TOKEN, "not found refresh token by refresh token");
        }
    }

    private RefreshToken util_getRefreshToken(RefreshToken refreshToken) {

        RefreshToken refreshTokenFound = refreshTokenRepository.getRefreshTokenByRefreshToken(refreshToken);

        if (refreshTokenFound != null) {
            return refreshTokenFound;
        } else {
            log.error("not found refresh token");

             throw new NotFoundException(NOT_FOUND_REFRESH_TOKEN, "not found refresh token");
        }
    }

    private Boolean util_isNotExistRefreshToken(Integer id) {

        RefreshToken refreshTokenFound = refreshTokenRepository.getById(id);

        if (refreshTokenFound == null) {
            return true;
        } else {

            log.warn("refresh token found by id");

             throw new AlReadyExistException(ALREADY_EXIST_REFRESH_TOKEN, "refresh token found by id");
        }
    }

    private Boolean util_isNotExistRefreshToken(User user) {

        RefreshToken refreshTokenFound = refreshTokenRepository
                .getRefreshTokenByUser(user);

        if (refreshTokenFound == null) {
            return true;
        } else {

            log.warn("refresh token found by refresh user");

            throw new AlReadyExistException(ALREADY_EXIST_REFRESH_TOKEN, "refresh token found by user");
        }
    }

    private Boolean util_isNotExistRefreshToken(String refreshToken) {

        RefreshToken refreshTokenFound = refreshTokenRepository.getByRefreshToken(refreshToken);

        if (refreshTokenFound == null) {
            return true;
        } else {

            log.warn("refresh token found by refresh token");

            throw new AlReadyExistException(ALREADY_EXIST_REFRESH_TOKEN,
                    "refresh token found by refresh token");
        }
    }

    private Boolean util_isNotExistRefreshToken(RefreshToken refreshToken) {

        RefreshToken refreshTokenFound = refreshTokenRepository.getRefreshTokenByRefreshToken(refreshToken);

        if (refreshTokenFound == null) {
            return true;
        } else {

            log.warn("refresh token found");

            throw new AlReadyExistException(ALREADY_EXIST_REFRESH_TOKEN, "refresh token found");
        }
    }

    private Boolean util_verifyRefreshToken(RefreshToken refreshToken) {
        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {

            refreshTokenRepository.delete(refreshToken);

            log.warn("refresh token expired and deleted");

            throw new NotValidException(NOT_VALID_REFRESH_TOKEN_EXPIRED, "");

        } else {
            return true;
        }
    }
}
