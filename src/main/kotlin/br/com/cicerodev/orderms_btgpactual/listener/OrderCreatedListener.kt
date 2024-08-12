package br.com.cicerodev.orderms_btgpactual.listener

import br.com.cicerodev.orderms_btgpactual.config.RabbitMqConfig.Companion.ORDER_CREATED_QUEUE
import br.com.cicerodev.orderms_btgpactual.listener.dto.OrderCreatedEvent
import br.com.cicerodev.orderms_btgpactual.service.OrderService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.Message
import org.springframework.stereotype.Component



@Component
class OrderCreatedListener(private val orderService: OrderService) {

    private val logger: Logger = LoggerFactory.getLogger(OrderCreatedListener::class.java)



    @RabbitListener(queues = [ORDER_CREATED_QUEUE])
    fun listen(message: Message<OrderCreatedEvent>) {
        logger.info("Message consumed: {}", message)

        orderService.save(message.payload)

    }
}