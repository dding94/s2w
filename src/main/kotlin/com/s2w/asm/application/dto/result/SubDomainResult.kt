package com.s2w.asm.application.dto.result

import com.s2w.asm.domain.Asm

data class SubDomainResult(
    val subDomain: String,
    val ip: String,
    val services: List<String>,
) {
    companion object {
        fun from(asm: Asm): SubDomainResult = SubDomainResult(
            subDomain = asm.subDomain,
            ip = asm.ip,
            services =  asm.software.map { it.title }
        )
    }
}
