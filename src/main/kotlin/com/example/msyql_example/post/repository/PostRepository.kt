package com.example.msyql_example.post.repository

import com.example.msyql_example.post.entity.Post
import org.springframework.data.repository.CrudRepository

interface PostRepository : CrudRepository<Post, Long>