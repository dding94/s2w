package com.s2w.asm.application

import com.s2w.asm.application.dto.command.AsmCreateCommand
import com.s2w.asm.application.dto.result.AsmCreateResult
import com.s2w.asm.application.dto.result.SoftwareResult
import com.s2w.asm.application.dto.result.SubDomainResult
import com.s2w.asm.application.processor.AsmCreateProcessor
import com.s2w.asm.application.processor.SoftwareFindProcessor
import com.s2w.asm.application.processor.SubDomainFindProcessor
import com.s2w.asm.common.response.PagedResponse
import org.springframework.stereotype.Service

@Service
class AsmService(
    private val asmCreateProcessor: AsmCreateProcessor,
    private val subDomainFindProcessor: SubDomainFindProcessor,
    private val softwareFindProcessor: SoftwareFindProcessor,
) {

    fun createAsm(command: List<AsmCreateCommand>): List<AsmCreateResult> {
        return asmCreateProcessor.execute(command)
    }

    fun findSubDomainsBySeedId(seedId: String, page: Int, size: Int): PagedResponse<SubDomainResult> {
        return subDomainFindProcessor.execute(seedId = seedId, page = page, size = size)
    }

    fun findSoftwaresBySeedId(seedId: String, page: Int, size: Int): PagedResponse<SoftwareResult> {
        return softwareFindProcessor.execute(seedId = seedId, page = page, size = size)
    }
}
