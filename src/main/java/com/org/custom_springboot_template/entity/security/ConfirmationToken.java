package entity.security;

import entity.primary.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ConfirmationTokens")
public class ConfirmationToken  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;

    @ManyToOne
    @JoinColumn(
            name = "UserId",
            nullable = false
    )
    private User user;

    @Column(name ="ConfirmationToken",nullable = false,unique = true )
    private String confirmationToken;

    @Column(name ="CreatedAt",nullable = false ,updatable = false)
    private LocalDateTime createdAt;

    @Column(name ="ExpiresAt",nullable = false )
    private LocalDateTime expiresAt;

    @Column(name = "ConfirmedAt")
    private LocalDateTime confirmedAt;
}
