package com.s2w.asm.infrastructure.adapter

import com.s2w.asm.common.response.PagedResponse
import com.s2w.asm.domain.Asm
import com.s2w.asm.domain.AsmQueryRepository
import com.s2w.asm.infrastructure.repository.query.AsmJdslRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class AsmQueryAdapter(
    private val asmJdslRepository: AsmJdslRepository,
) : AsmQueryRepository {

    override fun findAllBySeedId(seedId: String, page: Int, size: Int): PagedResponse<Asm> {
        return asmJdslRepository.findAllBySeedId(seedId = seedId, page = page, size = size)
    }
}
