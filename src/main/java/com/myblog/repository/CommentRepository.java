package com.myblog.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myblog.entity.BlogUser;
import com.myblog.entity.Comment;


@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

}
