package com.vivo.orders.orders.dto;

import com.vivo.orders.orders.model.ItemsDto;
import lombok.Data;
import lombok.Generated;

import java.util.List;
import java.util.UUID;


@Data
@Generated
public class RequestDto {

    private UUID id;

    private Long userId;

    private String status;

    private float totalPrice;

    private List<ItemsDto> items;

}
