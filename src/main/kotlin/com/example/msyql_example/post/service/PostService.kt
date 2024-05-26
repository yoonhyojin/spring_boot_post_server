package com.example.msyql_example.post.service

import com.example.msyql_example.common.exception.PostException
import com.example.msyql_example.post.dto.PostRequestDto
import com.example.msyql_example.post.dto.PostResponseDto
import com.example.msyql_example.post.entity.Post
import com.example.msyql_example.post.repository.PostRepository
import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.server.ResponseStatusException


@Service
class PostService {

    @Autowired
    private lateinit var postRepository: PostRepository

    /**
     * 모든 게시글을 가져옴.
     */
    fun getPosts() : List<PostResponseDto> {
        val result = postRepository.findAllByFetchJoin()
        return result.map { it.toResponse() }
    }

    /**
     * id를 통해 게시글을 반환함.
     * id가 존재하지 않는 경우 에러를 던짐.
     */
    fun getPostsById(id : Long) : PostResponseDto {
        val result = postRepository.findByIdOrNull(id)
            ?: throw PostException(msg = "존재하지 않는 게시글 ID입니다.")
        return result.toResponse()
    }

    /**
     * 사용자 ID로 게시글 가져오는 메소드
     */
    fun getPostByUserId(id : Long) : PostResponseDto {
        val result = postRepository.findByUserId(id)
            ?: throw PostException(msg = "해당 게시글이 존재하지 않습니다!")
        return result.toResponse()
    }

    /**
     * 새로운 게시글 생성 후 결과를 반환함.
     */
    fun postPosts(postRequestDto: PostRequestDto) : PostResponseDto {
        val result = postRepository.save(postRequestDto.toEntity())
        return result.toResponse()
    }

    /**
     * 게시글 수정하고 수정된 게시글을 반환함.
     */
    fun putPost(postRequestDto: PostRequestDto, id : Long) : PostResponseDto {
        val post : Post = postRepository.findByIdOrNull(id)
            ?: throw PostException(msg = "존재하지 않는 게시글 ID입니다.")

        val newPost : PostRequestDto = PostRequestDto(
            id = post.id,
            title = postRequestDto.title,
            post = postRequestDto.post,
            userId = postRequestDto.userId,
            isPublic = postRequestDto.isPublic,
        )

        val result = postRepository.save(newPost.toEntity())
        return result.toResponse()
    }

    /**
     * 게시글을 삭제함.
     */
    fun deletePost(id : Long) : Unit {
        postRepository.deleteById(id)
    }
}