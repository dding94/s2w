package com.s2w.asm.application.processor

import com.s2w.asm.application.AsmValidator
import com.s2w.asm.application.dto.result.SoftwareResult
import com.s2w.asm.common.response.PagedResponse
import com.s2w.asm.domain.AsmQueryRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SoftwareFindProcessor(
    private val asmQueryRepository: AsmQueryRepository,
    private val asmValidator: AsmValidator,
) {

    @Transactional(readOnly = true)
    fun execute(seedId: String, page: Int, size: Int): PagedResponse<SoftwareResult> {
        val pagedAsms = asmQueryRepository.findAllBySeedId(seedId = seedId, page = page, size = size)
        pagedAsms.contents.forEach { it.validateSeedId(seedId) }

        return pagedAsms.mapContents { SoftwareResult.from(it) }
    }
}
