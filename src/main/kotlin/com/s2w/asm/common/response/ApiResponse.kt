package com.s2w.asm.common.response

import com.s2w.asm.common.exception.ExceptionType

class ApiResponse<T>(

    val errorCode: String? = null,
    val message: String,
    val data: T?,
) {

    companion object {
        fun <T> success(data: T) = ApiResponse(
            message = "success",
            data = data
        )


        fun error(exceptionType: ExceptionType) = ApiResponse(
            errorCode = exceptionType.errorCode,
            message = exceptionType.message,
            data = null
        )

        fun error(exceptionType: ExceptionType, message: String) = ApiResponse(
            errorCode = exceptionType.errorCode,
            message = message,
            data = null
        )
    }
}