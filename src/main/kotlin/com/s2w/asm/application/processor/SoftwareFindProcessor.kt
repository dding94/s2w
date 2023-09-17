package com.s2w.asm.application.processor

import com.s2w.asm.application.AsmValidator
import com.s2w.asm.application.dto.result.SoftwareResult
import com.s2w.asm.common.response.PagedResponse
import com.s2w.asm.domain.AsmQueryRepository
import org.springframework.stereotype.Component

@Component
class SoftwareFindProcessor(
    private val asmQueryRepository: AsmQueryRepository,
    private val asmValidator: AsmValidator,
) {
    fun execute(seedId: String, page: Int, size: Int): PagedResponse<SoftwareResult> {
        val pagedAsmList = asmQueryRepository.findAllBySeedId(seedId = seedId, page = page, size = size)
        asmValidator.validateAsmList(pagedAsmList, seedId)

        return pagedAsmList.mapContents { SoftwareResult.from(it) }
    }
}
