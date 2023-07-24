package com.vivo.orders.orders.repository;

import com.vivo.orders.orders.model.ResultDto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrdersRepository extends JpaRepository<ResultDto, Long> {



}
