package com.s2w.asm.presentation.dto.response

import com.s2w.asm.application.dto.result.SoftwareResult
import java.time.LocalDateTime

data class SoftwareResponse(
    val seedId: String,
    val softwares: List<String>,
    val createTime: LocalDateTime,
    val updateTime: LocalDateTime,
) {
    companion object {
        fun from(result: SoftwareResult): SoftwareResponse = SoftwareResponse(
            seedId = result.seedId,
            softwares = result.softwares,
            createTime = result.createTime,
            updateTime = result.updateTime,
        )
    }
}
