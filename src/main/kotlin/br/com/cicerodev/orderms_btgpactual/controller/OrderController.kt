package br.com.cicerodev.orderms_btgpactual.controller

import br.com.cicerodev.orderms_btgpactual.controller.dto.ApiResponse
import br.com.cicerodev.orderms_btgpactual.controller.dto.OrderResponse
import br.com.cicerodev.orderms_btgpactual.controller.dto.PaginationResponse
import br.com.cicerodev.orderms_btgpactual.service.OrderService
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(private val orderService: OrderService) {

    @GetMapping("/customers/{customerId}/orders")
    fun listOrders(
        @PathVariable("customerId") customerId: Long,
        @RequestParam(name = "page", defaultValue = "0") page: Int,
        @RequestParam(name = "pageSize", defaultValue = "10") pageSize: Int
    ): ResponseEntity<ApiResponse<OrderResponse>> {

        val pageResponse = orderService.findAllByCustomerId(customerId, PageRequest.of(page, pageSize))
        val totalOnOrders = orderService.findTotalOnOrdersByCustomerId(customerId)

        return ResponseEntity.ok(
            ApiResponse(
                mapOf(
                    "totalOnOrders" to totalOnOrders
                ),
                pageResponse.content,
                PaginationResponse.fromPage(pageResponse)
            )
        )
    }
}