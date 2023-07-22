package com.vivo.orders.orders.controller;

import com.vivo.orders.orders.dto.ItemsDto;
import com.vivo.orders.orders.dto.ProductsDto;
import com.vivo.orders.orders.dto.ResultDto;
import com.vivo.orders.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class OrderController {

    @Autowired
    private OrderService service ;

//    @GetMapping("/products")
//    public ResponseEntity<ProductsDto> findProducts() {
//        return new ResponseEntity<ProductsDto>(service.findProducts(), HttpStatus.OK);
//    }

    @PostMapping("/createOrders")
    public ResponseEntity<ResultDto> createOrders(
            @RequestBody ResultDto resultDto
    ){
        return new ResponseEntity<>(service.order(resultDto.getUserId(), resultDto.getProducts()), HttpStatus.OK
        );
    }
}
