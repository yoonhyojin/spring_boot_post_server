package com.example.msyql_example.member.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class LoginDto(

    @field:NotBlank(message = "이메일을 입력하세요!")
    @field:Email(message = "올바르지 않은 이메일형식입니다!")
    @JsonProperty("email")
    private val _email : String?,


    @field:NotBlank(message = "비밀번호를 입력하세요!")
    @JsonProperty("password")
    private val _password : String?,
) {
    val email : String
        get() = _email!!
    val password : String
        get() = _password!!
}
