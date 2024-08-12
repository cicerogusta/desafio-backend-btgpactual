package br.com.cicerodev.orderms_btgpactual.service

import br.com.cicerodev.orderms_btgpactual.controller.dto.OrderResponse
import br.com.cicerodev.orderms_btgpactual.entity.OrderEntity
import br.com.cicerodev.orderms_btgpactual.entity.OrderItem
import br.com.cicerodev.orderms_btgpactual.listener.dto.OrderCreatedEvent
import br.com.cicerodev.orderms_btgpactual.reposiitory.OrderRepository
import org.bson.Document
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregate
import org.springframework.data.mongodb.core.aggregation.Aggregation.*
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
data class OrderService(
    private val orderRepository: OrderRepository,
    private val mongoTemplate: MongoTemplate
) {
    fun save(event: OrderCreatedEvent) {

        val entity = OrderEntity(orderId = event.codigoPedido, customerId = event.codigoCliente);

        entity.itens = getOrderItems(event)
        entity.total = getTotal(event)
        orderRepository.save(entity)

    }

    fun findAllByCustomerId(customerId: Long, pageRequest: PageRequest): Page<OrderResponse> {
        val orders = orderRepository.findAllByCustomerId(customerId, pageRequest)

        return orders.map { orderEntity -> OrderResponse.fromEntity(orderEntity) }

    }

    fun findTotalOnOrdersByCustomerId(customerId: Long): BigDecimal {

        val aggregation = newAggregation(match(Criteria.where("customerId").`is`(customerId)), group().sum("total").`as`("total"))


        val response = mongoTemplate.aggregate<Document>(aggregation, "tb_orders");

        return BigDecimal(response.uniqueMappedResult?.get("total").toString())

    }

    private fun getTotal(event: OrderCreatedEvent): BigDecimal {
        return event.itens
            .stream().map { item ->
                item.preco.multiply(BigDecimal(item.quantidade))

            }.reduce(BigDecimal::add).orElse(BigDecimal.ZERO)

    }

    private fun getOrderItems(
        event: OrderCreatedEvent
    ): List<OrderItem> {
        return event.itens.stream()
            .map { item ->
                OrderItem(item.produto, item.quantidade, item.preco)

            }.toList()
    }
}
