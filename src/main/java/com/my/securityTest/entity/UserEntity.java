package com.my.securityTest.entity;

import com.my.securityTest.dto.UserRole;
import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    // username 중복 안되게
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

}
