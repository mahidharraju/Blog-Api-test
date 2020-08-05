package com.myblog.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myblog.entity.BlogUser;


@Repository
public interface UserRepository extends JpaRepository<BlogUser, UUID> {

}
