package com.s2w.asm.application.processor

import com.s2w.asm.application.dto.command.AsmCreateCommand
import com.s2w.asm.application.dto.result.AsmCreateResult
import com.s2w.asm.domain.Asm
import com.s2w.asm.domain.AsmCommandRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AsmCreateProcessor(
    private val asmCommandRepository: AsmCommandRepository
) {

    @Transactional
    fun execute(command: List<AsmCreateCommand>): List<AsmCreateResult> {
        val asmList = createAsmList(command)
        val savedAsmList = asmCommandRepository.saveAllAsm(asmList)

        return savedAsmList.map { AsmCreateResult.from(it) }
    }

    private fun createAsmList(command: List<AsmCreateCommand>): List<Asm> {
        val asmList = command.map {
            val seedId = it.seedId.split("_").last()

            Asm.create(
                subDomain = it.subDomain,
                status = it.status,
                ip = it.ip,
                softwares = it.softwares,
                seedId = seedId,
            )
        }
        return asmList
    }
}
