package br.com.cicerodev.orderms_btgpactual.controller.dto

import org.springframework.data.domain.Page

data class PaginationResponse(
    var page: Int,
    var pageSize:Int,
    var totalElements: Long,
    var totalPages: Int
) {
    companion object {
        fun fromPage(page: Page<OrderResponse>): PaginationResponse {
            return PaginationResponse(
                page.number,
                page.size,
                page.totalElements,
                page.totalPages
            )

        }
    }
}
