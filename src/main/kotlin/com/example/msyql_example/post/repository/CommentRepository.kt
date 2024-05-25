package com.example.msyql_example.post.repository

import com.example.msyql_example.post.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long?>