package com.example.msyql_example.member.service

import com.example.msyql_example.common.exception.member.InvalidEmailException
import com.example.msyql_example.member.dto.MemberRequestDto
import com.example.msyql_example.member.entity.Member
import com.example.msyql_example.member.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    /**
     * 회원가입
     */
    fun signUp(memberRequestDto: MemberRequestDto) : String {
        val member : Member? = memberRepository.findByEmail(memberRequestDto.email)
        if (member != null) {
            throw InvalidEmailException(fieldName = "email", message = "이미 가입한 이메일입니다!")
        }
        memberRepository.save(memberRequestDto.toEntity())
        return "회원가입에 성공했습니다!"
    }
}