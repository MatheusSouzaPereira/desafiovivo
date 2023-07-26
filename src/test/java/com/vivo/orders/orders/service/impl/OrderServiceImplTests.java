package com.vivo.orders.orders.service.impl;


import com.vivo.orders.orders.dto.ProductsDto;
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

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings
public class OrderServiceImplTests {

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    UserDto userDto = new UserDto();
    @Mock
    private OrdersRepository repository;

    @Mock
    private BufferedReader bufferedReader;

    ResultDto fooBar = new ResultDto();
    @Test
    void testUserNotNull() throws Exception {
        when(repository.save(any())).thenReturn(Utils.usingResultDtoStatusPending());

        ResultDto resul = orderServiceImpl.order(Utils.USERID_VALID, Utils.usingListDto());
        resul.setId(UUID.randomUUID());

         assertNotNull(resul.getId());
         assertEquals(Utils.usingResultDtoStatusPending().getStatus(), resul.getStatus());
        assertEquals(Utils.usingResultDtoStatusPending().getUserId(), resul.getUserId());

    }

    @Test
    void testUserNotExist() throws Exception {
        UserDto user = orderServiceImpl.findByUsers(Utils.USERID_INVALID);
        assertNull(user);

    }

    @Test
    void testUpdateStatusNotNull() throws Exception {
        ResultDto resultDto = orderServiceImpl.updateStatus(Utils.usingResultDtoStatusPending());

        assertEquals(resultDto.getStatus(), Utils.RESULTDTO_STATUS_PENDING);

    }
    @Test
    void testFindByProductsException() {
        assertThrows(Exception.class, () -> {
           assertNull(orderServiceImpl.findByProducts(10000L));
        });
    }

    @Test
    void testFindByProductsDuplicate() throws Exception {
        ResultDto resultDto = orderServiceImpl.order(Utils.USERID_VALID, Utils.usingListItemsDto());

        assertNotNull(resultDto);
      assertEquals(1, resultDto.getItems().size());
    }

}
