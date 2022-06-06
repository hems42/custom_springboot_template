package com.org.custom_springboot_template.entity.security;

import com.org.custom_springboot_template.entity.primary.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "RefreshTokens")
public class RefreshToken {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer tokenId;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
  private User user;

  @Column(nullable = false, unique = true)
  private String refreshToken;

  @Column(nullable = false)
  private Instant expiryDate;

  @Column(name = "CreatedDate", updatable = false)
  private LocalDateTime createdDate;
}
