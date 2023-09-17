package com.s2w.asm.presentation.dto.response

import com.s2w.asm.application.dto.result.AsmCreateResult
import java.time.LocalDateTime

data class AsmCreateResponse(
    val subDomain: String,
    val status: String,
    val ip: String,
    val softwares: List<String>,
    val seedId: String,
    val createTime: LocalDateTime,
    val updateTime: LocalDateTime,
) {
    companion object {
        fun from(result: AsmCreateResult): AsmCreateResponse = AsmCreateResponse(
            subDomain = result.subDomain,
            status = result.status,
            ip = result.ip,
            seedId = result.seedId,
            softwares = result.softwares,
            createTime = result.createTime,
            updateTime = result.updateTime,
        )
    }
}
