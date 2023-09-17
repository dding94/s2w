package com.s2w.asm.domain

interface AsmCommandRepository {

    fun saveAllAsm(asm: List<Asm>): List<Asm>
}
