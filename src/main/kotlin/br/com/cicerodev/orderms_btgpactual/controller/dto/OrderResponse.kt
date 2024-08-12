package br.com.cicerodev.orderms_btgpactual.controller.dto

import br.com.cicerodev.orderms_btgpactual.entity.OrderEntity
import java.math.BigDecimal

data class OrderResponse(
    var orderId: Long,
    var customerId: Long,
    var total: BigDecimal

) {
    companion object {
        fun fromEntity(entity: OrderEntity): OrderResponse {
            return OrderResponse(entity.orderId, entity.customerId, entity.total)
        }
    }
}


