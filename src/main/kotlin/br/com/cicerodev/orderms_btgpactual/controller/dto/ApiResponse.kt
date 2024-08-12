package br.com.cicerodev.orderms_btgpactual.controller.dto

data class ApiResponse<T>(
    var summary: Map<String, Any>,
    var data: List<T>,
    var pagination: PaginationResponse
)
