package com.org.custom_springboot_template.entity.log;

import com.org.custom_springboot_template.product.enums.ProductEnumTransactionTypes;
import entity.primary.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LogUsers")
public class LogUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ProductEnumTransactionTypes transactionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId", nullable = false)
    private User user;

    @Column(name = "CreatedDate", updatable = false)
    private LocalDateTime createdDate;
}
