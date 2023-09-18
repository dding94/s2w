package com.s2w.asm.application.processor

import com.s2w.asm.application.AsmValidator
import com.s2w.asm.application.dto.result.SubDomainResult
import com.s2w.asm.common.response.PagedResponse
import com.s2w.asm.domain.AsmQueryRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SubDomainFindProcessor(
    private val asmQueryRepository: AsmQueryRepository,
    private val asmValidator: AsmValidator,
) {

    @Transactional(readOnly = true)
    fun execute(seedId: String, page: Int, size: Int): PagedResponse<SubDomainResult> {
        val pagedAsmList = asmQueryRepository.findAllBySeedId(seedId = seedId, page = page, size = size)
        asmValidator.validateAsmList(pagedAsmList, seedId)

        return pagedAsmList.mapContents { SubDomainResult.from(it) }
    }
}
