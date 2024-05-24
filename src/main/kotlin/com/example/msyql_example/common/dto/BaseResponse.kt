package com.example.msyql_example.common.dto

import com.example.msyql_example.common.enums.ResultStatus

data class BaseResponse<T>(
    var status : String = ResultStatus.SUCCESS.name,
    var data : T? = null,
    var resultMsg : String = ResultStatus.SUCCESS.msg,
)
