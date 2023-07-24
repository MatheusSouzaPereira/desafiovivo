package com.vivo.orders.orders.service;

import com.vivo.orders.orders.model.ItemsDto;
import com.vivo.orders.orders.model.ResultDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {

        ResultDto order(Long id , List<ItemsDto> products ) throws Exception;

        ResultDto updateStatus(UUID id , Long userId, String status ) throws Exception;
}
