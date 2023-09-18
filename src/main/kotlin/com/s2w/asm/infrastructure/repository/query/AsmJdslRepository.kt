package com.s2w.asm.infrastructure.repository.query

import com.linecorp.kotlinjdsl.query.spec.expression.EntitySpec
import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.querydsl.from.fetch
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.listQuery
import com.linecorp.kotlinjdsl.spring.data.singleQuery
import com.s2w.asm.common.response.PagedResponse
import com.s2w.asm.domain.Asm
import org.springframework.stereotype.Repository

@Repository
class AsmJdslRepository(
    private val queryFactory: SpringDataQueryFactory,
) {

    fun findAllBySeedId(seedId: String, page: Int, size: Int): PagedResponse<Asm> {
        val asmIds: List<Long> = queryFactory.listQuery {
            val entity: EntitySpec<Asm> = entity(Asm::class)

            select(col(Asm::id))
            from(entity)
            where(col(Asm::seedId).equal(seedId))
            limit(size)
            offset((page - 1) * size)
        }

        val asmList = queryFactory.listQuery {
            val entity: EntitySpec<Asm> = entity(Asm::class)
            select(entity)
            from(entity)
            fetch(Asm::software)
            where(col(Asm::id).`in`(asmIds))
        }

        val total: Int = queryFactory.singleQuery<Long> {
            val entity: EntitySpec<Asm> = entity(Asm::class)

            select(count(entity))
            from(entity)
            where(col(Asm::seedId).equal(seedId))
        }.toInt()

        return PagedResponse(
            contents = asmList,
            totalElements = total,
            size = size,
            currentPage = page
        )
    }
}
