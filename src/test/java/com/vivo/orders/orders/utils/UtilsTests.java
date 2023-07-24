package com.vivo.orders.orders.utils;

import com.vivo.orders.orders.model.ItemsDto;
import com.vivo.orders.orders.model.ResultDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class UtilsTests {

    @InjectMocks
    private Utils utils = new Utils();

    @Mock
    HttpURLConnection conexao;


    @Test
    void testFindByUsersException(){
        assertThrows(Exception.class, () -> {
            when(utils.findByUsers(10000L)).thenThrow(new Exception());
            assertNull(utils.findByUsers(10000L));
        });
    }

}
