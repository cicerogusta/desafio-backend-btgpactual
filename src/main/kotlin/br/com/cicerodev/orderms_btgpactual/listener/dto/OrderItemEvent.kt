package br.com.cicerodev.orderms_btgpactual.listener.dto

import java.math.BigDecimal

data class OrderItemEvent(
    var produto: String ,
    var quantidade: Int,
    var preco: BigDecimal
) {

}
