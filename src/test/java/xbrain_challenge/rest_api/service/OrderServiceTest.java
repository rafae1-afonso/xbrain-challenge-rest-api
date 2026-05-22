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
import xbrain_challenge.rest_api.database.entity.OrderEntity;
import xbrain_challenge.rest_api.database.object.ProductObject;
import xbrain_challenge.rest_api.database.repository.IOrderRepository;
import xbrain_challenge.rest_api.dto.OrderDto;
import xbrain_challenge.rest_api.mapper.OrderMapper;
import xbrain_challenge.rest_api.producer.OrderQueueProducer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private IOrderRepository orderRepository;
    @Mock
    private OrderMapper mapper;
    @Mock
    private OrderQueueProducer orderQueueProducer;

    @InjectMocks
    private OrderService orderService;

    @Captor
    private ArgumentCaptor<OrderDto> orderDtoArgumentCaptor;

    @Nested
    class listAll {

        @Test
        @DisplayName("Should return list of orders")
        void should_ReturnListOfOrders() {

            List<OrderEntity> orderList = new ArrayList<>();

            doReturn(orderList).when(orderRepository).findAll();

            var output = orderService.listAll();

            assertNotNull(output);

            verify(orderRepository, times(1)).findAll();
        }
    }

    @Nested
    class create {

        @Test
        @DisplayName("Should create a order")
        void should_CreateOrder() {

            ArrayList<ProductObject> productsList = new ArrayList<>();
            productsList.add(ProductObject.builder()
                            .productCode("PRODUCT-000")
                            .price(BigDecimal.TEN)
                            .quantity(2)
                    .build());

            OrderEntity orderEntity = OrderEntity.builder()
                    .id(UUID.randomUUID())
                    .customerCode("CUSTOMER-000")
                    .deliveryAddress("Street Delivery Address, 999")
                    .products(productsList)
                    .totalValue(BigDecimal.ZERO)
                            .build();

            OrderDto orderDto = OrderDto.builder()
                    .customerCode("CUSTOMER-000")
                    .deliveryAddress("Street Delivery Address, 999")
                    .products(productsList)
                    .build();

            doReturn(orderEntity).when(mapper).toEntity(orderDtoArgumentCaptor.capture());

            var output = orderService.create(orderDto);

            assertNotNull(output);

            var orderDtoCaptured = orderDtoArgumentCaptor.getValue();

            assertEquals(orderDto, orderDtoCaptured);

            verify(mapper, times(1)).toEntity(orderDtoCaptured);
        }

        @Test
        @DisplayName("Should throw exception when an error occurs")
        void should_ThrowException_WhenErrorOccurs() {
            doThrow(new RuntimeException()).when(mapper).toEntity(null);

            assertThrows(RuntimeException.class, () -> orderService.create(null));
        }
    }

}