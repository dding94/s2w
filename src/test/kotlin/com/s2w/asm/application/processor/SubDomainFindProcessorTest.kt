package com.s2w.asm.application.processor

import com.s2w.asm.application.AsmValidator
import com.s2w.asm.application.dto.result.SubDomainResult
import com.s2w.asm.common.response.PagedResponse
import com.s2w.asm.domain.Asm
import com.s2w.asm.domain.AsmQueryRepository
import com.s2w.asm.domain.exception.SeedIdNotFoundException
import io.mockk.mockk
import io.mockk.every
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class SubDomainFindProcessorTest {
    private val asmQueryRepository: AsmQueryRepository = mockk()
    private val asmValidator: AsmValidator = mockk()

    private val sut = SubDomainFindProcessor(asmQueryRepository, asmValidator)

    @Test
    fun `seedId 를 통해 서브도메인 목록을 조회할 수 있다 - 성공`() {
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
        val pagedResponse = PagedResponse(asms, asms.size, size, page)
        val expectedPagedSubDomainResult = pagedResponse.mapContents { SubDomainResult.from(it) }

        every { asmQueryRepository.findAllBySeedId(seedId, page, size) } returns pagedResponse
        every { asmValidator.validateAsms(pagedResponse, seedId) } returns Unit

        // When
        val result = sut.execute(seedId, page, size)

        // Then
        verify(exactly = 1) { asmQueryRepository.findAllBySeedId(seedId, page, size) }
        verify(exactly = 1) { asmValidator.validateAsms(pagedResponse, seedId) }
        Assertions.assertThat(result.contents).isEqualTo(expectedPagedSubDomainResult.contents)
        Assertions.assertThat(result.totalElements).isEqualTo(expectedPagedSubDomainResult.totalElements)
        Assertions.assertThat(result.size).isEqualTo(expectedPagedSubDomainResult.size)
        Assertions.assertThat(result.currentPage).isEqualTo(expectedPagedSubDomainResult.currentPage)
    }

    @Test
    fun `seedId가 존재하지 않는 경우 SeedIdNotFoundException을 발생시킨다 - 실패`() {
        // Given
        val seedId = "SEED_8664d1e419"
        val page = 1
        val size = 10

        val pagedResponse = PagedResponse(emptyList<Asm>(), 0, size, page)

        every { asmQueryRepository.findAllBySeedId(seedId, page, size) } returns pagedResponse
        every { asmValidator.validateAsms(pagedResponse, seedId) } throws SeedIdNotFoundException()

        // When & Then
        assertThrows(SeedIdNotFoundException::class.java) {
            sut.execute(seedId, page, size)
        }
        verify(exactly = 1) { asmQueryRepository.findAllBySeedId(seedId, page, size) }
        verify(exactly = 1) { asmValidator.validateAsms(pagedResponse, seedId) }
    }
}
