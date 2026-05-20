package xbrain_challenge.rest_api.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xbrain_challenge.rest_api.database.repository.IOrderRepository;
import xbrain_challenge.rest_api.mapper.OrderMapper;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private IOrderRepository orderRepository;
    private OrderMapper mapper;

    @InjectMocks
    private OrderService orderService;


}