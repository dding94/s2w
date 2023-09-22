package com.s2w.asm.application.processor

import com.s2w.asm.application.AsmValidator
import com.s2w.asm.application.dto.result.SoftwareResult
import com.s2w.asm.common.response.PagedResponse
import com.s2w.asm.domain.Asm
import com.s2w.asm.domain.AsmQueryRepository
import com.s2w.asm.domain.exception.SeedIdNotFoundException
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class SoftwareFindProcessorTest {
    private val asmQueryRepository: AsmQueryRepository = mockk()
    private val asmValidator: AsmValidator = mockk()

    private val sut = SoftwareFindProcessor(asmQueryRepository, asmValidator)

    @Test
    fun `seedId에 따라 소프트웨어를 조회할 수 있다 - 성공`() {
        // Given
        val seedId = "SEED_8664d1e419"
        val page = 1
        val size = 2

        val asm1 = Asm.create("cafe.naver.com", "active", "3.38.222.16", listOf("Nginx", "Tomcat"), seedId)
        asm1.setTestTime()
        val asm2 = Asm.create("nid.naver.com", "active", "52.78.116.214", listOf("React", "Java"), seedId)
        asm2.setTestTime()
        val asm3 = Asm.create("shopping.naver.com", "active", "43.201.3.5", listOf("PWA", "styled-components", "Vue.js"), seedId)
        asm3.setTestTime()

        val asms = listOf(asm1, asm2, asm3)

        val softwareResults = asms.map { SoftwareResult.from(it) }
        val pagedAsmResponse = PagedResponse(asms, asms.size, size, page)
        val expectedPagedSoftwareResult = PagedResponse(softwareResults, softwareResults.size, size, page)

        every { asmQueryRepository.findAllBySeedId(seedId, page, size) } returns pagedAsmResponse
        justRun { asmValidator.validateAsms(any(), any()) }

        // When
        val result = sut.execute(seedId, page, size)

        // Then
        verify(exactly = 1) { asmQueryRepository.findAllBySeedId(seedId, page, size) }
        verify(exactly = 1) { asmValidator.validateAsms(pagedAsmResponse, seedId) }

        assertThat(result.contents).isEqualTo(expectedPagedSoftwareResult.contents)
        assertThat(result.totalElements).isEqualTo(expectedPagedSoftwareResult.totalElements)
        assertThat(result.size).isEqualTo(expectedPagedSoftwareResult.size)
        assertThat(result.currentPage).isEqualTo(expectedPagedSoftwareResult.currentPage)
    }

    @Test
    fun `seedId에 따른 소프트웨어가 없을 경우 SeedIdNotFoundException을 발생시킨다 - 실패`() {
        // Given
        val seedId = "SEED_8664d1e419"
        val page = 1
        val size = 2

        val emptyPagedAsmResponse = PagedResponse(emptyList<Asm>(), 0, size, page)

        every { asmQueryRepository.findAllBySeedId(seedId, page, size) } returns emptyPagedAsmResponse
        every { asmValidator.validateAsms(emptyPagedAsmResponse, seedId) } throws SeedIdNotFoundException()

        // When & Then
        assertThrows(SeedIdNotFoundException::class.java) {
            sut.execute(seedId, page, size)
        }
        verify(exactly = 1) { asmQueryRepository.findAllBySeedId(seedId, page, size) }
        verify(exactly = 1) { asmValidator.validateAsms(emptyPagedAsmResponse, seedId) }
    }
}
