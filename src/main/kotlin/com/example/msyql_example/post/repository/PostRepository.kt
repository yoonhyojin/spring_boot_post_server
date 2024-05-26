package com.example.msyql_example.post.repository

import com.example.msyql_example.post.entity.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface PostRepository : JpaRepository<Post, Long> {
    fun findByUserId(id : Long) : Post

    @Query(value = "SELECT p FROM Post p LEFT JOIN FETCH p.comments")
    fun findAllByFetchJoin() : List<Post>
}