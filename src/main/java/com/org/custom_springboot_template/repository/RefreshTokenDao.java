package com.org.custom_springboot_template.repository;
import com.org.custom_springboot_template.entity.primary.User;
import com.org.custom_springboot_template.entity.security.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RefreshTokenDao extends JpaRepository<RefreshToken, Integer> {

    @Query( "SELECT r " +
            "FROM RefreshTokens r " +
            "WHERE r.refreshToken = ?1")
    RefreshToken getByRefreshToken(String refreshToken);

    @Query( "SELECT r " +
            "FROM RefreshTokens r " +
            "WHERE r.user = ?1")
    RefreshToken getRefreshTokenByUser(User user);

    @Query( "SELECT r " +
            "FROM RefreshTokens  r " +
            "WHERE  r = ?1")
    RefreshToken getRefreshTokenByRefreshToken(RefreshToken refreshToken);

    @Transactional
    @Modifying
    @Query( "DELETE " +
            "FROM RefreshTokens r " +
            "WHERE r = ?1")
    Integer deleteByRefreshToken(RefreshToken refreshToken);

    @Transactional
    @Modifying
    @Query( "DELETE " +
            "FROM RefreshTokens r " +
            "WHERE r.user = ?1")
    Integer deleteByUser(User user);

}
