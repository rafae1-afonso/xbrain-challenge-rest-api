package xbrain_challenge.rest_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xbrain_challenge.rest_api.database.entity.DeliveryEntity;
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

        DeliveryEntity newDelivery = mapper.toEntity(deliveryDto);
        deliveryRepository.save(newDelivery);

        return newDelivery;
    }
}
