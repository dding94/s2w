package com.s2w.asm.domain

import com.s2w.asm.common.response.PagedResponse

interface AsmQueryRepository {

    fun findAllBySeedId(seedId: String, page: Int, size: Int): PagedResponse<Asm>
}
