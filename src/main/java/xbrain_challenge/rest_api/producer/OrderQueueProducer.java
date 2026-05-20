package xbrain_challenge.rest_api.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import xbrain_challenge.rest_api.config.RabbitMqConfig;
import xbrain_challenge.rest_api.database.entity.OrderEntity;

@Service
@RequiredArgsConstructor
public class OrderQueueProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendToQueue(OrderEntity orderEntity) {
        rabbitTemplate.convertAndSend("", RabbitMqConfig.ORDER_QUEUE, orderEntity);
    }
}
