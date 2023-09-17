package com.s2w.asm.domain

import com.s2w.asm.common.BaseTimeEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Asm(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    val subDomain: String,

    val status: String,

    val ip: String,

    @OneToMany(mappedBy = "asm", cascade = [CascadeType.ALL], orphanRemoval = true)
    val software: MutableList<Software> = mutableListOf(),

    val seedId: String,
) : BaseTimeEntity() {

    companion object {
        fun create(subDomain: String, status: String, ip: String, softwares: List<String>, seedId: String): Asm {
            val asm = Asm(
                subDomain = subDomain,
                status = status,
                ip = ip,
                seedId = seedId,
            )
            asm.addAllSoftware(softwares.map { Software(title = it, asm = asm) })

            return asm
        }
    }

    fun addAllSoftware(softwares: List<Software>) {
        software.addAll(softwares)
    }
}
