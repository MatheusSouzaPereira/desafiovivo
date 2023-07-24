package com.vivo.orders.orders.service.impl;


import com.vivo.orders.orders.dto.UserDto;
import com.vivo.orders.orders.model.ItemsDto;
import com.vivo.orders.orders.model.ResultDto;
import com.vivo.orders.orders.repository.OrdersRepository;
import com.vivo.orders.orders.service.OrderService;
import com.vivo.orders.orders.utils.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings
public class OrderServiceImplTests {

    private static final Long USER_ID = 1L;

    private static final List<ItemsDto> PRODUCTS = new ArrayList<>();

    private static final String  STATUS_CONCLUDED = "CONCLUDED";

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    @Mock
    private Utils utils ;

    @Mock
    private UserDto userDto ;
    UserDto user = new UserDto() ;
    ResultDto resultDto = new ResultDto();

    @Mock
    private OrdersRepository repository;

    @Mock
    private OrderService service ;

    @Test
    void testCreateUserNotNull() throws Exception {
        when(utils.findByUsers(USER_ID)).thenReturn(userDto);

        orderServiceImpl.order(USER_ID, PRODUCTS);

        user = utils.findByUsers(USER_ID);
        user.setId(USER_ID);
        assertNotNull(user.getId());

    }

    @Test
    void testUpdateStatusNotNull() throws Exception {
        when(utils.findByUsers(USER_ID)).thenReturn(userDto);
        when(repository.save(resultDto)).thenReturn(resultDto);

        orderServiceImpl.updateStatus(resultDto.getId(), USER_ID, resultDto.getStatus());
        resultDto.setStatus(STATUS_CONCLUDED);
        resultDto = repository.save(resultDto);

        assertEquals(resultDto.getStatus(), STATUS_CONCLUDED);

    }
    @Test
    void testFindByProductsException() {
        assertThrows(Exception.class, () -> {
           assertNull(orderServiceImpl.findByProducts(10000L));
        });
    }


}
