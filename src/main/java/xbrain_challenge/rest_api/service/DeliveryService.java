package xbrain_challenge.rest_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import xbrain_challenge.rest_api.database.entity.DeliveryEntity;
import xbrain_challenge.rest_api.database.entity.OrderEntity;
import xbrain_challenge.rest_api.database.repository.IDeliveryRepository;
import xbrain_challenge.rest_api.dto.DeliveryDto;
import xbrain_challenge.rest_api.mapper.DeliveryMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final IDeliveryRepository deliveryRepository;
    private final DeliveryMapper mapper;

    public List<DeliveryEntity> listAll() {
        return deliveryRepository.findAll();
    }

    public DeliveryEntity create(DeliveryDto deliveryDto) {

        DeliveryEntity newDeliveryEntity = mapper.toEntity(deliveryDto);

        deliveryRepository.save(newDeliveryEntity);

        return newDeliveryEntity;
    }

    public DeliveryDto buildDeliveryDtoFromMessage(Message<OrderEntity> message) {
        return DeliveryDto.builder()
                .order_id(message.getPayload().getId())
                .deliveryAddress(message.getPayload().getDeliveryAddress())
                .build();
    }
}
