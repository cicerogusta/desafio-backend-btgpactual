package br.com.cicerodev.orderms_btgpactual.entity

import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import java.math.BigDecimal

class OrderItem(
    var product: String,
    var quantity: Int,
    @Field(targetType = FieldType.DECIMAL128)
    var price: BigDecimal
) {

}
