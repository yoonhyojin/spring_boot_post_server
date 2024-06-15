package com.example.msyql_example.post.dto

import com.example.msyql_example.post.entity.Comment
import com.example.msyql_example.post.entity.Post

data class CommentRequestDto(
    val id : Long?,
    val content : String,
    var post : Post?,
) {
    fun toEntity() : Comment = Comment(
        id = id,
        content = content,
        post = post!!
    )
}

data class CommentResponseDto(
    val id : Long?,
    val content : String,
)
