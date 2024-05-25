package com.example.msyql_example.post.entity

import com.example.msyql_example.post.dto.CommentResponseDto
import jakarta.persistence.*

@Entity
class Comment(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id : Long?,

    @Column(nullable = false, length = 1000)
    var content : String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = ForeignKey(name = "fk_comment_post_id"))
    val post : Post,
) {
    fun toResponse() : CommentResponseDto = CommentResponseDto(
        id = id,
        content = content
    )
}