package com.s2w.asm.domain

import com.s2w.asm.common.BaseTimeEntity
import jakarta.persistence.*

@Entity
class Software(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val title: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "refinement_product_group_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var asm: Asm
) : BaseTimeEntity()
