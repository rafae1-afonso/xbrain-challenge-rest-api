package xbrain_challenge.rest_api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import xbrain_challenge.rest_api.database.entity.DeliveryEntity;
import xbrain_challenge.rest_api.dto.DeliveryDto;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {
    @Mapping(target = "deliveryAddress", source = "deliveryAddress")
    @Mapping(target = "order", source = "order")
    DeliveryEntity toEntity(DeliveryDto orderDto);
}
