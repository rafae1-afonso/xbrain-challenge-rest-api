package xbrain_challenge.rest_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import xbrain_challenge.rest_api.database.entity.OrderEntity;
import xbrain_challenge.rest_api.database.object.ProductObject;
import xbrain_challenge.rest_api.dto.OrderDto;
import xbrain_challenge.rest_api.service.OrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private OrderService orderService;

    @Captor
    private ArgumentCaptor<OrderDto> orderDtoArgumentCaptor;

    @Nested
    class listAll {

        @Test
        @DisplayName("Should response be list of orders")
        void should_ResponseListOfOrders() throws Exception {

            List<OrderEntity> orderList = new ArrayList<>();

            doReturn(orderList).when(orderService).listAll();

            MvcResult mvcResult = mockMvc.perform(get("/v1/pedidos"))
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            assertNotNull(mvcResult);
        }
    }

    @Nested
    class createOrder {

        @Test
        @DisplayName("Should response be created order")
        void should_ResponseCreatedOrder() throws Exception {

            OrderEntity orderEntity = OrderEntity.builder().build();

            ArrayList<ProductObject> productsList = new ArrayList<>();
            productsList.add(ProductObject.builder()
                    .productCode("PRODUCT-000")
                    .price(BigDecimal.TEN)
                    .quantity(2)
                    .build());

            OrderDto orderDto = OrderDto.builder()
                    .customerCode("CUSTOMER-000")
                    .deliveryAddress("Street Delivery Address, 999")
                    .totalValue(BigDecimal.TEN)
                    .products(productsList)
                    .build();

            doReturn(orderEntity).when(orderService).create(orderDtoArgumentCaptor.capture());

            MvcResult mvcResult = mockMvc.perform(post("/v1/pedidos")
                            .content(objectMapper.writeValueAsString(orderDto))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andReturn();

            assertNotNull(mvcResult);
        }
    }
}