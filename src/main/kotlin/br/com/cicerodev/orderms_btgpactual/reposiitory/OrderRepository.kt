package br.com.cicerodev.orderms_btgpactual.reposiitory

import br.com.cicerodev.orderms_btgpactual.entity.OrderEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.repository.MongoRepository

interface OrderRepository : MongoRepository<OrderEntity, Long> {

    fun findAllByCustomerId(customerId: Long, pageRequest: PageRequest): Page<OrderEntity>
}