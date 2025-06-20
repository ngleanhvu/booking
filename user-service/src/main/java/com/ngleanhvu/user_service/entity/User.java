package com.ngleanhvu.user_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user",
        indexes = {
                @Index(name = "idx_user_status", columnList = "active"),
                @Index(name = "idx_user_role", columnList = "role")
        })
@Getter @Setter
public class User {

    @Id
    private String id;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "full_name", length = 70, nullable = false)
    private String fullName;

    @Column(length = 70, nullable = false, unique = true)
    private String username;

    @Column(length = 15, nullable = false, unique = true)
    private String phone;

    @Column(length = 255, nullable = false)
    private String avatar = "";

    @Column(nullable = false)
    private Boolean active = true;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
        updatedDate = createdDate;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = LocalDateTime.now();
    }

}
