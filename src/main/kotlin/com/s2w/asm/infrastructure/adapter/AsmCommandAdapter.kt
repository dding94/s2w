package com.s2w.asm.infrastructure.adapter

import com.s2w.asm.domain.Asm
import com.s2w.asm.domain.AsmCommandRepository
import com.s2w.asm.infrastructure.repository.AsmRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AsmCommandAdapter(
    private val asmRepository: AsmRepository,
) : AsmCommandRepository {

    override fun saveAllAsm(asm: List<Asm>): List<Asm> {
        return asmRepository.saveAll(asm)
    }
}
