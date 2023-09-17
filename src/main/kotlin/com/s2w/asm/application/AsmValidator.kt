package com.s2w.asm.application

import com.s2w.asm.common.response.PagedResponse
import com.s2w.asm.domain.Asm
import com.s2w.asm.domain.exception.SeedIdNotFoundException
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class AsmValidator {
    private val logger = KotlinLogging.logger { }

    fun validateAsmList(pagedAsmList: PagedResponse<Asm>, seedId: String) {
        if (pagedAsmList.contents.isEmpty()) {
            logger.info("등록된 seedId 가 없습니다. seedId = $seedId")
            throw SeedIdNotFoundException()
        }
    }
}
