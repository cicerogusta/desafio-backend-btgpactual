package br.com.cicerodev.orderms_btgpactual.entity

import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId
import java.math.BigDecimal

@Document(collection = "tb_orders")
class OrderEntity(
    @MongoId
    val orderId: Long,
    @Indexed(name = "customer_id_index")
    val customerId: Long,
    @Field(targetType = FieldType.DECIMAL128)
    var total: BigDecimal = BigDecimal(0),
    var itens: List<OrderItem> = emptyList()

)
