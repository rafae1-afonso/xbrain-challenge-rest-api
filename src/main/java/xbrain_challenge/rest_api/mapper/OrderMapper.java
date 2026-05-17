package xbrain_challenge.rest_api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import xbrain_challenge.rest_api.database.entity.OrderEntity;
import xbrain_challenge.rest_api.dto.OrderDto;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "customerCode", source = "customerCode")
    @Mapping(target = "totalValue", source = "totalValue")
    @Mapping(target = "deliveryAddress", source = "deliveryAddress")
    OrderEntity toEntity(OrderDto orderDto);
}
