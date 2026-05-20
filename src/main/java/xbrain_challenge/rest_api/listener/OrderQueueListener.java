package xbrain_challenge.rest_api.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import xbrain_challenge.rest_api.config.RabbitMqConfig;
import xbrain_challenge.rest_api.database.entity.OrderEntity;
import xbrain_challenge.rest_api.dto.DeliveryDto;
import xbrain_challenge.rest_api.service.DeliveryService;

@Component
@RequiredArgsConstructor
public class OrderQueueListener {

    private final DeliveryService deliveryService;

    DeliveryDto buildDeliveryDTO(Message<OrderEntity> message) {
        return DeliveryDto.builder()
                .order_id(message.getPayload().getId())
                .deliveryAddress(message.getPayload().getDeliveryAddress())
                .build();
    }

    @RabbitListener(queues = RabbitMqConfig.ORDER_QUEUE)
        public void listen(Message<OrderEntity> message) {

        deliveryService.create(buildDeliveryDTO(message));
    }
}
