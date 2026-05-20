package xbrain_challenge.rest_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xbrain_challenge.rest_api.database.entity.OrderEntity;
import xbrain_challenge.rest_api.database.repository.IOrderRepository;
import xbrain_challenge.rest_api.dto.OrderDto;
import xbrain_challenge.rest_api.mapper.OrderMapper;
import xbrain_challenge.rest_api.producer.OrderQueueProducer;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final IOrderRepository orderRepository;
    private final OrderMapper mapper;
    private final OrderQueueProducer orderQueueProducer;

    public List<OrderEntity> listAll() {
        return orderRepository.findAll();
    }

    public OrderEntity create(OrderDto orderDto) {

        OrderEntity newOrderEntity = mapper.toEntity(orderDto);

        orderRepository.save(newOrderEntity);

        orderQueueProducer.sendToQueue(newOrderEntity);

        return newOrderEntity;
    }
}
