package com.s2w.asm.application.dto.result

import com.s2w.asm.domain.Asm
import java.time.LocalDateTime

data class SoftwareResult(
    val seedId: String,
    val softwares: List<String>,
    val createTime: LocalDateTime,
    val updateTime: LocalDateTime,
) {
    companion object {
        fun from(asm: Asm): SoftwareResult = SoftwareResult(
            seedId = asm.seedId,
            softwares = asm.software.map { it.title },
            createTime = asm.createTime,
            updateTime = asm.updateTime,
        )
    }
}
