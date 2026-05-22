package xbrain_challenge.rest_api.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import xbrain_challenge.rest_api.database.entity.DeliveryEntity;
import xbrain_challenge.rest_api.database.entity.OrderEntity;
import xbrain_challenge.rest_api.database.repository.IDeliveryRepository;
import xbrain_challenge.rest_api.dto.DeliveryDto;
import xbrain_challenge.rest_api.dto.OrderDto;
import xbrain_challenge.rest_api.mapper.DeliveryMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceTest {

    @Mock
    private IDeliveryRepository deliveryRepository;
    @Mock
    private DeliveryMapper mapper;
    @Mock
    private DeliveryDto deliveryDto;

    @InjectMocks
    private DeliveryService deliveryService;

    @Captor
    private ArgumentCaptor<DeliveryDto> deliveryDtoArgumentCaptor;

    @Nested
    class listAll {

        @Test
        @DisplayName("Should return list of deliveries")
        void should_ReturnListOfDeliveries() {

            List<DeliveryEntity> deliveryList = new ArrayList<>();

            doReturn(deliveryList).when(deliveryRepository).findAll();

            var output = deliveryService.listAll();

            assertNotNull(output);
        }
    }

    @Nested
    class create {

        @Test
        @DisplayName("Should create a delivery with success")
        void should_CreateDelivery_WithSuccess() {

            DeliveryEntity deliveryEntity = DeliveryEntity.builder()
                    .id(UUID.randomUUID())
                    .order_id(UUID.randomUUID())
                    .deliveryAddress("Street Delivery Address, 999")
                    .build();

            DeliveryDto deliveryDto = DeliveryDto.builder()
                    .order_id(UUID.randomUUID())
                    .deliveryAddress("Street Delivery Address, 999")
                    .build();

            doReturn(deliveryEntity).when(mapper).toEntity(deliveryDtoArgumentCaptor.capture());

            var output = deliveryService.create(deliveryDto);

            assertNotNull(output);

            var deliveryDtoCaptured = deliveryDtoArgumentCaptor.getValue();

            assertEquals(deliveryDto, deliveryDtoCaptured);

            verify(mapper, times(1)).toEntity(deliveryDtoCaptured);
        }

        @Test
        @DisplayName("Should throw exception when an error occurs")
        void should_ThrowException_WhenErrorOccurs() {

            doThrow(new RuntimeException()).when(mapper).toEntity(null);

            assertThrows(RuntimeException.class, () -> deliveryService.create(null));
        }
    }

    @Test
    @DisplayName("Should build and return DeliveryDto from Message")
    void should_BuildAndReturnDeliveryDtoFromMessage() {

        OrderEntity orderEntity = OrderEntity.builder()
                .id(UUID.randomUUID())
                .deliveryAddress("Street Delivery Address, 999")
                .build();

        Message<OrderEntity> message = MessageBuilder.withPayload(orderEntity).build();

        DeliveryDto result = deliveryService.buildDeliveryDtoFromMessage(message);

        assertNotNull(result);
    }
}