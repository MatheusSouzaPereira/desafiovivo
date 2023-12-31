package com.vivo.orders.orders.controller;

import com.vivo.orders.orders.dto.RequestDto;
import com.vivo.orders.orders.model.ResultDto;
import com.vivo.orders.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:8081")
public class OrderController {

    @Autowired
    private OrderService service ;

    @PostMapping("/createOrders")
    public ResponseEntity<ResultDto> createOrders(@RequestBody RequestDto resultDto) throws Exception {
        return new ResponseEntity<>(service.order(resultDto.getUserId(), resultDto.getItems() ), HttpStatus.OK);
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<ResultDto> updateStatus(@RequestBody ResultDto resultDto) throws Exception {
        return new ResponseEntity<>(service.updateStatus(resultDto), HttpStatus.OK);
    }
}

