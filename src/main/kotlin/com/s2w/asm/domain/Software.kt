package com.s2w.asm.domain

import com.s2w.asm.common.BaseTimeEntity
import jakarta.persistence.*

@Entity
class Software(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    val title: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asm_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var asm: Asm
) : BaseTimeEntity() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Software) return false

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
