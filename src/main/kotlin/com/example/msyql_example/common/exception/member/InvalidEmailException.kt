package com.example.msyql_example.common.exception.member

class InvalidEmailException(
    val fieldName : String = "",
    message : String = "에러가 발생했습니다!"
) : RuntimeException(message)