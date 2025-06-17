package com.ngleanhvu.auth_service.repository;

import com.ngleanhvu.auth_service.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, Integer> {
}
