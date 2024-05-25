package com.example.msyql_example.post.repository

import com.example.msyql_example.post.entity.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface PostRepository : JpaRepository<Post, Long> {
    fun findByUserId(id : Long) : Post
}