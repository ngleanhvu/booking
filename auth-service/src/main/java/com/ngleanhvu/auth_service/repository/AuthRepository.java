package com.ngleanhvu.auth_service.repository;

import com.ngleanhvu.auth_service.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, String> {
    Optional<Auth> findByEmail(String email);
}
