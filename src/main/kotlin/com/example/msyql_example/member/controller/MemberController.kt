package com.example.msyql_example.member.controller

import com.example.msyql_example.common.dto.BaseResponse
import com.example.msyql_example.common.dto.CustomUser
import com.example.msyql_example.common.dto.TokenInfo
import com.example.msyql_example.member.dto.LoginDto
import com.example.msyql_example.member.dto.MemberRequestDto
import com.example.msyql_example.member.dto.MemberResponseDto
import com.example.msyql_example.member.service.MemberService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@Tag(name = "회원 Api 컨트롤러", description =  "회원가입, 로그인, 내정보 조회 Api 명세서입니다.")
@RestController
@RequestMapping("/api/member")
class MemberController(
    private val memberService: MemberService
) {
    /**
     * 회원가입 Api
     */
    @PostMapping("/join")
    private fun signUp(@Valid @RequestBody memberRequestDto: MemberRequestDto)
    : ResponseEntity<BaseResponse<String>> {
        val result = memberService.signUp(memberRequestDto)
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(BaseResponse(data = result))
    }

    /**
     * 로그인 Api
     */
    @PostMapping("/login")
    private fun login(@Valid @RequestBody loginDto: LoginDto)
    : ResponseEntity<BaseResponse<TokenInfo>> {
        val tokenInfo = memberService.login(loginDto)
        return ResponseEntity.status(HttpStatus.OK)
            .body(BaseResponse(data = tokenInfo))
    }

    @SecurityRequirement(name = "BearerAuth")
    //내 정보 조회 api
    @GetMapping("/info/{id}")
    private fun searchMyInfo(@PathVariable id : Long)
    :ResponseEntity<BaseResponse<MemberResponseDto>> {
        val id  = (SecurityContextHolder.getContext().authentication.principal as CustomUser).id
        val result = memberService.searchMyInfo(id)
        return ResponseEntity.status(HttpStatus.OK)
            .body(BaseResponse(data =result))

    }

}