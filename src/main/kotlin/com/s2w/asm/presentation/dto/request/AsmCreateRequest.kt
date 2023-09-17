package com.s2w.asm.presentation.dto.request

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.s2w.asm.application.dto.command.AsmCreateCommand
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class AsmCreateRequests(
    @field:Valid
    val asmCreateRequest: List<AsmCreateRequest>
) {
    fun toCommand(): List<AsmCreateCommand> {
        return asmCreateRequest.map { it.toCommand() }
    }

    companion object {
        @JsonCreator
        @JvmStatic
        fun createFromJsonList(jsonList: List<AsmCreateRequest>): AsmCreateRequests {
            return AsmCreateRequests(jsonList)
        }
    }

    @JsonValue
    fun toJsonList(): List<AsmCreateRequest> {
        return asmCreateRequest
    }
}

data class AsmCreateRequest(
    @field:NotBlank(message = "subDomain not blank.")
    val subDomain: String?,

    @field:NotBlank(message = "status not blank.")
    val status: String?,

    @field:NotBlank(message = "ip not blank.")
    @field:Pattern(
        regexp = "^(25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)$",
        message = "IPv4 format 형식에 맞지 않습니다.",
    )
    val ip: String?,

    val softwares: List<String>,

    @field:NotBlank(message = "seedId not blank")
    @field:Pattern(
        regexp = "^SEED_[a-zA-Z0-9]{10}$",
        message = "잘못된 seedId 형식입니다."
    )
    val seedId: String?,
) {
    fun toCommand() = AsmCreateCommand(
        subDomain = subDomain!!,
        status = status!!,
        ip = ip!!,
        softwares = softwares,
        seedId = seedId!!,
    )
}
