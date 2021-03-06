package com.org.custom_springboot_template.entity.log;

import com.org.custom_springboot_template.entity.primary.User;
import com.org.custom_springboot_template.entity.security.Role;
import com.org.custom_springboot_template.product.enums.ProductEnumTransactionTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LogRoles")
public class LogRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ProductEnumTransactionTypes transactionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RoleId",nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId", nullable = false)
    private User user;

    @Column(name = "CreatedDate", updatable = false)
    private LocalDateTime createdDate;
}
