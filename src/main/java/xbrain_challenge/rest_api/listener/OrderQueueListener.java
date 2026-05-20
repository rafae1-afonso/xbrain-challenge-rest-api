package xbrain_challenge.rest_api.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import xbrain_challenge.rest_api.config.RabbitMqConfig;
import xbrain_challenge.rest_api.database.entity.OrderEntity;
import xbrain_challenge.rest_api.service.DeliveryService;

@Component
@RequiredArgsConstructor
public class OrderQueueListener {

    private final DeliveryService deliveryService;

    @RabbitListener(queues = RabbitMqConfig.ORDER_QUEUE)
    public void listen(Message<OrderEntity> message) {

        deliveryService.create(deliveryService.buildDeliveryDtoFromMessage(message));
    }
}
