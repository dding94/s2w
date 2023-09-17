package com.s2w.asm.common.response

data class PagedResponse<T>(
    val contents: List<T>,
    val totalElements: Int,
    val size: Int,
    val currentPage: Int
) {
    val numberOfElements: Int = contents.size
    val totalPage: Int = (totalElements + size - 1) / size
    val first: Boolean = currentPage == 1
    val last: Boolean = totalElements <= currentPage * size

    fun <R> mapContents(transform: (T) -> R): PagedResponse<R> {
        val newContents = contents.map(transform)

        return PagedResponse(
            contents = newContents,
            totalElements = totalElements,
            size = size,
            currentPage = currentPage
        )
    }
}