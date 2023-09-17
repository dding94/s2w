package com.s2w.asm.infrastructure.repository

import com.s2w.asm.domain.Asm
import org.springframework.data.jpa.repository.JpaRepository

interface AsmRepository : JpaRepository<Asm, Long> {

}
