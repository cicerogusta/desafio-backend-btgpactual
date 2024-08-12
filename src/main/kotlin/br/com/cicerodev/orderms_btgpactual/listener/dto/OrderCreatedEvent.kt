package br.com.cicerodev.orderms_btgpactual.listener.dto

data class OrderCreatedEvent(var codigoPedido: Long,
                        var codigoCliente: Long,
                        var itens: List<OrderItemEvent>
) {

}
