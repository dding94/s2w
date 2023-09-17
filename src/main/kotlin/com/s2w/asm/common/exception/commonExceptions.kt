package com.s2w.asm.common.exception

abstract class BaseException(
    val exceptionType: ExceptionType,
) : RuntimeException(exceptionType.message)
