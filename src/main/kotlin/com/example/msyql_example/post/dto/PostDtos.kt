package com.example.msyql_example.post.dto

import com.example.msyql_example.post.entity.Post

data class PostRequestDto(
    var id : Long?,
    var title : String,
    var post : String,
    var userId : Long,
    var isPublic : Boolean
) {
    fun toEntity() : Post = Post(
        id = id,
        title = title,
        post = post,
        userId = userId,
        isPublic = isPublic
    )
}


data class PostResponseDto(
    var id : Long?,
    var title : String,
    var post : String,
    var userId : Long,
    var isPublic : Boolean
)