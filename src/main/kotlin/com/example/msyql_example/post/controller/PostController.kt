package com.example.msyql_example.post.controller

import com.example.msyql_example.common.dto.BaseResponse
import com.example.msyql_example.post.dto.PostRequestDto
import com.example.msyql_example.post.dto.PostResponseDto
import com.example.msyql_example.post.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/posts")
class PostController {

    @Autowired
    private lateinit var postService: PostService


    /**
     * 모든 게시글을 가져오는 Api
     */
    @GetMapping
    private fun getPosts() : ResponseEntity<BaseResponse<List<PostResponseDto>>> {
        val result = postService.getPosts()
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse(data = result))
    }

    /**
     * id를 통해 게시글을 가져오는 Api
     */
    @GetMapping("/{id}")
    private fun getPostsById(@PathVariable id : Long) : ResponseEntity<BaseResponse<PostResponseDto>> {
        val result = postService.getPostsById(id)
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse(data = result))
    }

    /**
     * 게시글을 생성하는 Api
     */
    @PostMapping
    private fun postPost(@RequestBody postRequestDto: PostRequestDto) :
            ResponseEntity<BaseResponse<PostResponseDto>> {
        val result = postService.postPosts(postRequestDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse(data = result))
    }

    /**
     * 게시글 수정 Api
     */
    @PutMapping("/{id}")
    private fun putPost(@RequestBody postRequestDto: PostRequestDto, @PathVariable id : Long) :
            ResponseEntity<BaseResponse<PostResponseDto>> {
        val result = postService.putPost(postRequestDto, id)
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse(data = result))
    }

    /**
     * id를 통해 게시글을 삭제하는 Api
     */
    @DeleteMapping("/{id}")
    private fun deletePost(@PathVariable id : Long) : ResponseEntity<BaseResponse<Any>> {
        postService.deletePost(id)
        return ResponseEntity.ok().body(BaseResponse(data = null))
    }
}