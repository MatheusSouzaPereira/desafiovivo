package com.vivo.orders.orders.service;

import com.vivo.orders.orders.dto.ItemsDto;
import com.vivo.orders.orders.dto.ResultDto;

import java.util.List;

public interface OrderService {

        ResultDto order(Long id , List<ItemsDto> products ) throws Exception;
}
