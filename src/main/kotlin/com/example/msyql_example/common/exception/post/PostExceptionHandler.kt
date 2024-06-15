package com.example.msyql_example.common.exception

import com.example.msyql_example.common.dto.BaseResponse
import com.example.msyql_example.common.enums.ResultStatus
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ResponseStatusException

@Order(value = 1)
@RestControllerAdvice
class PostExceptionHandler {

    @ExceptionHandler(PostException::class)
    protected fun noPostExceptionHandler(exception : PostException) :
            ResponseEntity<BaseResponse<Any>> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                BaseResponse(
                status = ResultStatus.ERROR.name, resultMsg = exception.msg
            )
            )
    }
}