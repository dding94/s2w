package com.s2w.asm.application.processor

import com.s2w.asm.application.dto.command.AsmCreateCommand
import com.s2w.asm.domain.Asm
import com.s2w.asm.domain.AsmCommandRepository
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import io.mockk.verify
import org.junit.jupiter.api.Test

class AsmCreateProcessorTest {
    private val asmCommandRepository: AsmCommandRepository = mockk()

    private val sut: AsmCreateProcessor = AsmCreateProcessor(
        asmCommandRepository
    )

    @Test
    fun `seedResult 스캔결과 등록 요청이 들어왔을 때 성공한다 - 성공한다`() {
        // Given
        val asmCreateCommands = listOf(
            createAsmCreateCommand("cafe.naver.com", "active", "3.38.222.16", listOf("Nginx", "Tomcat"), "SEED_8664d1e419"),
            createAsmCreateCommand("nid.naver.com", "active", "52.78.116.214", listOf("React", "Java"), "SEED_8664d1e419"),
            createAsmCreateCommand("shopping.naver.com", "active", "43.201.3.5", listOf("PWA", "styled-components", "Vue.js"), "SEED_8664d1e419")
        )
        val expectedAsms = asmCreateCommands.map {
            val asm = Asm.create(it.subDomain, it.status, it.ip, it.softwares, it.seedId.split("_").last())
            asm.setTestTime()
            asm
        }
        every { asmCommandRepository.saveAllAsm(any()) } returns expectedAsms

        // When
        val result = sut.execute(asmCreateCommands)

        // Then
        verify(exactly = 1) { asmCommandRepository.saveAllAsm(expectedAsms) }
        assertThat(result.size).isEqualTo(expectedAsms.size)
    }

    private fun createAsmCreateCommand(subDomain: String, status: String, ip: String, softwares: List<String>, seedId: String): AsmCreateCommand = AsmCreateCommand(
        subDomain = subDomain,
        status = status,
        ip = ip,
        softwares = softwares,
        seedId = seedId,
    )
}
