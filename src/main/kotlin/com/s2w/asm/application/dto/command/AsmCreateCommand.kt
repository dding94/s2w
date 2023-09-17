package com.s2w.asm.application.dto.command

data class AsmCreateCommand(
    val subDomain: String,
    val status: String,
    val ip: String,
    val softwares: List<String>,
    val seedId: String
)
