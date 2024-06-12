package com.example.msyql_example.common.exception.member

import com.example.msyql_example.common.dto.BaseResponse
import com.example.msyql_example.common.enums.ResultStatus
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@Order(value = 1)
@RestControllerAdvice
class MemberExceptionHandler {

    @ExceptionHandler(InvalidEmailException::class)
    protected fun invalidEmailExceptionHandler(exception: InvalidEmailException)
    : ResponseEntity<BaseResponse<Map<String, String>>> {
        val error = mapOf(exception.fieldName to (exception.message ?: "Not Exception Message"))
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(BaseResponse(
                status = ResultStatus.ERROR.name,
                data = error,
                resultMsg = ResultStatus.ERROR.msg,
            ))
    }

    @ExceptionHandler(BadCredentialsException::class)
    protected fun badCredentialExceptionHandler(exception : BadCredentialsException)
    : ResponseEntity<BaseResponse<Map<String, String>>> {
        val errors = mapOf("로그인 실패" to "이메일 혹은 비밀번호를 확인하세요!")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            BaseResponse(
                status = ResultStatus.ERROR.name,
                data = errors,
                resultMsg = ResultStatus.ERROR.msg
            )
        )
    }

}