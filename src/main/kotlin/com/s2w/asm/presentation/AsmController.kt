package com.s2w.asm.presentation

import com.s2w.asm.application.AsmService
import com.s2w.asm.common.response.ApiResponse
import com.s2w.asm.common.response.PagedResponse
import com.s2w.asm.presentation.dto.request.AsmCreateRequest
import com.s2w.asm.presentation.dto.request.AsmCreateRequests
import com.s2w.asm.presentation.dto.response.AsmCreateResponse
import com.s2w.asm.presentation.dto.response.SoftwareResponse
import com.s2w.asm.presentation.dto.response.SubDomainResponse
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/asms")
class AsmController(
    private val asmService: AsmService,
) {

    @PostMapping
    fun createAsm(
        @Valid @RequestBody request: AsmCreateRequests,
    ): ApiResponse<List<AsmCreateResponse>> {
        val result = asmService.createAsm(request.toCommand())

        return ApiResponse.success(result.map { AsmCreateResponse.from(it) })
    }

    @GetMapping("/{seedId}/sub-domains")
    fun findSubDomainsBySeedId(
        @PathVariable seedId: String,
        @RequestParam(value = "size", defaultValue = "2") size: Int,
        @RequestParam(value = "page", defaultValue = "1") page: Int,
    ): ApiResponse<PagedResponse<SubDomainResponse>> {
        val result = asmService.findSubDomainsBySeedId(seedId = seedId, page = page, size = size)

        return ApiResponse.success(result.mapContents { SubDomainResponse.from(it) })
    }

    @GetMapping("/{seedId}/softwares")
    fun findSoftwaresBySeedId(
        @PathVariable seedId: String,
        @RequestParam(value = "size", defaultValue = "2") size: Int,
        @RequestParam(value = "page", defaultValue = "1") page: Int,
    ): ApiResponse<PagedResponse<SoftwareResponse>> {
        val result = asmService.findSoftwaresBySeedId(seedId = seedId, page = page, size = size)

        return ApiResponse.success(result.mapContents { SoftwareResponse.from(it) })
    }
}
