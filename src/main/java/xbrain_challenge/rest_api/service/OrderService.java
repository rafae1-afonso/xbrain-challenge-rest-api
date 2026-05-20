package xbrain_challenge.rest_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xbrain_challenge.rest_api.database.entity.OrderEntity;
import xbrain_challenge.rest_api.database.repository.IOrderRepository;
import xbrain_challenge.rest_api.dto.OrderDto;
import xbrain_challenge.rest_api.mapper.OrderMapper;
import xbrain_challenge.rest_api.producer.OrderQueueProducer;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final IOrderRepository orderRepository;
    private final OrderMapper mapper;
    private final OrderQueueProducer orderQueueProducer;

    private BigDecimal calculateTotalOrderValue(OrderDto orderDto) {
        return orderDto.getProducts().stream().map(order -> order.getPrice()
                .multiply(new BigDecimal(order.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<OrderEntity> listAll() {
        return orderRepository.findAll();
    }

    public OrderEntity create(OrderDto orderDto) {

        OrderEntity newOrderEntity = mapper.toEntity(orderDto);

        newOrderEntity.setTotalValue(calculateTotalOrderValue(orderDto));

        orderRepository.save(newOrderEntity);

        orderQueueProducer.sendToQueue(newOrderEntity);

        return newOrderEntity;
    }
}
