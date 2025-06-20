package com.ngleanhvu.auth_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "auth")
@Entity
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
public class Auth {
    @Id
    private String id;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    private Integer status = 1;
}
