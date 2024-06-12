package com.example.msyql_example.member.service

import com.example.msyql_example.common.authority.JwtTokenProvider
import com.example.msyql_example.common.dto.TokenInfo
import com.example.msyql_example.common.enums.Role
import com.example.msyql_example.common.exception.member.InvalidEmailException
import com.example.msyql_example.member.dto.LoginDto
import com.example.msyql_example.member.dto.MemberRequestDto
import com.example.msyql_example.member.entity.Member
import com.example.msyql_example.member.entity.MemberRole
import com.example.msyql_example.member.repository.MemberRepository
import com.example.msyql_example.member.repository.MemberRoleRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberRoleRepository: MemberRoleRepository,
    private val jwtTokenProvider: JwtTokenProvider,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
) {

    /**
     * 회원가입
     */
    fun signUp(memberRequestDto: MemberRequestDto) : String {
        var member : Member? = memberRepository.findByEmail(memberRequestDto.email)
        if (member != null) {
            throw InvalidEmailException(fieldName = "email", message = "이미 가입한 이메일입니다!")
        }
        member = memberRequestDto.toEntity()
        memberRepository.save(member)
        val memberRole = MemberRole(
            id = null,
            role = Role.MEMBER,
            member = member,
        )
        memberRoleRepository.save(memberRole)
        return "회원가입에 성공했습니다!"
    }

    /**
     * 로그인
     */
    fun login(loginDto: LoginDto) : TokenInfo {
        val authenticationToken = UsernamePasswordAuthenticationToken(loginDto.email, loginDto.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)
        return jwtTokenProvider.createToken(authentication)
    }
}