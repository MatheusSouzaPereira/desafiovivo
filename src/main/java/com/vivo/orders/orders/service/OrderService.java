package com.vivo.orders.orders.service;

import com.vivo.orders.orders.model.ItemsDto;
import com.vivo.orders.orders.model.ResultDto;

import java.util.List;

public interface OrderService {

        ResultDto order(Long id , List<ItemsDto> products ) throws Exception;

        ResultDto updateStatus(ResultDto resultDto) throws Exception;
}
