package com.vivo.orders.orders.repository;

import com.vivo.orders.orders.dto.ItemsDto;
import com.vivo.orders.orders.dto.ResultDto;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;



public interface OrdersRepository extends JpaRepository<ResultDto, Long> {



}
