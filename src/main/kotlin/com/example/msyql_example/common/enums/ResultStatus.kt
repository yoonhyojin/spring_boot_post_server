package com.example.msyql_example.common.enums

enum class ResultStatus(val msg : String) {
    SUCCESS("요청이 성공했습니다!"),
    ERROR("에러가 발생했습니다!")
}