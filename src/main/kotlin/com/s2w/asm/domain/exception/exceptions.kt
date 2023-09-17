package com.s2w.asm.domain.exception

import com.s2w.asm.common.exception.BaseException
import com.s2w.asm.common.exception.ExceptionType

class SeedIdNotFoundException(
    exceptionType: ExceptionType = ExceptionType.ERR_404_SEED_ID_NOT_FOUND,
) : BaseException(exceptionType)
