package com.myblog.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myblog.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {

}
