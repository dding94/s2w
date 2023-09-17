package com.s2w.asm.presentation.dto.response

import com.s2w.asm.application.dto.result.SubDomainResult

data class SubDomainResponse(
    val subDomain: String,
    val ip: String,
    val services: List<String>,
) {
    companion object {
        fun from(subDoResult: SubDomainResult): SubDomainResponse = SubDomainResponse(
            subDomain = subDoResult.subDomain,
            ip = subDoResult.ip,
            services = subDoResult.services
        )
    }
}
