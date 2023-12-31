package com.s2w.asm.common

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity {
    @CreatedDate
    @Column(nullable = false, updatable = false)
    lateinit var createTime: LocalDateTime

    @LastModifiedDate
    @Column(nullable = false)
    lateinit var updateTime: LocalDateTime
        protected set

    internal fun setTestTime() {
        this.createTime = LocalDateTime.now()
        this.updateTime = LocalDateTime.now()
    }
}

