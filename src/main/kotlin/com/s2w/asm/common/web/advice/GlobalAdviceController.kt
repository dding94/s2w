package com.s2w.asm.common.web.advice

import com.s2w.asm.common.response.ApiResponse
import com.s2w.asm.common.exception.BaseException
import com.s2w.asm.common.exception.ExceptionType
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalAdviceController {

    private val logger = KotlinLogging.logger { }

    /**
     * 400 Bad request
     * spring validation error
     */
    @ExceptionHandler(BindException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBindingRequest(e: BindException): ApiResponse<Nothing> {
        val errorMessage = e.bindingResult.fieldErrors
            .map { it.defaultMessage }
            .joinToString("\n")

        logger.info("binding exception message: $errorMessage")

        return ApiResponse.error(ExceptionType.ERR_400_PARAMS_ERROR, errorMessage)
    }

    /**
     * 400 BadRequest
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): ApiResponse<Nothing> {
        logger.warn(e.message)

        return ApiResponse.error(ExceptionType.ERR_400_PARAMS_ERROR)
    }

    @ExceptionHandler(BaseException::class)
    fun handleCustomException(e: BaseException): ResponseEntity<ApiResponse<Nothing>> {
        val errorType = e.exceptionType
        logger.info(e.message, e)

        return ResponseEntity
            .status(errorType.httpStatusType.status)
            .body(ApiResponse.error(errorType))
    }

    /**
     * 500 Internal Server
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    private fun handleException(exception: Exception): ApiResponse<Nothing> {
        logger.error(exception.message, exception)

        return ApiResponse.error(ExceptionType.ERR_500_INTERNAL_SERVER)
    }
}
