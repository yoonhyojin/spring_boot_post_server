package com.example.msyql_example.post.controller

import com.example.msyql_example.common.dto.BaseResponse
import com.example.msyql_example.post.dto.CommentRequestDto
import com.example.msyql_example.post.dto.CommentResponseDto
import com.example.msyql_example.post.service.CommentService
import com.fasterxml.jackson.databind.ser.Serializers.Base
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/posts/comments")
class CommentController {

    @Autowired
    private lateinit var commentService: CommentService

    @GetMapping
    private fun getComments() :
            ResponseEntity<BaseResponse<List<CommentResponseDto>>> {
        val result = commentService.getComments()
        return ResponseEntity
            .status(HttpStatus.OK).body(BaseResponse(data = result))
    }

    @PostMapping("/{id}")
    private fun postComment(@PathVariable id : Long, @RequestBody commentRequestDto: CommentRequestDto) :
            ResponseEntity<BaseResponse<CommentResponseDto>> {
        val result = commentService.postComment(id, commentRequestDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse(data = result))
    }
}