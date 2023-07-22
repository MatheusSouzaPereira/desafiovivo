package com.vivo.orders.orders.service.impl;

import com.vivo.orders.orders.dto.ItemsDto;
import com.vivo.orders.orders.dto.ProductsDto;
import com.vivo.orders.orders.dto.ResultDto;
import com.vivo.orders.orders.repository.OrdersRepository;
import com.vivo.orders.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private  OrdersRepository repository;

    @Override
    public ResultDto order(Long id, List<ItemsDto> products ) {
        System.out.println(id);
        System.out.println(products);
       Long abc = products.stream().map(a -> a.getId()).count();
        repository.findByItens(abc);
        return null;
    }




}
