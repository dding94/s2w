package com.s2w.asm.application.dto.result

import com.s2w.asm.domain.Asm
import java.time.LocalDateTime

data class AsmCreateResult(
    val subDomain: String,
    val status: String,
    val ip: String,
    val softwares: List<String>,
    val seedId: String,
    val createTime: LocalDateTime,
    val updateTime: LocalDateTime,
) {
    companion object {
        fun from(asm: Asm): AsmCreateResult = AsmCreateResult(
            subDomain = asm.subDomain,
            status = asm.status,
            ip = asm.ip,
            seedId = asm.seedId,
            softwares = asm.software.map { it.title },
            createTime = asm.createTime,
            updateTime = asm.updateTime,
        )
    }
}
