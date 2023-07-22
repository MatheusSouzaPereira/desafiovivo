package com.vivo.orders.orders.dto;

import lombok.Data;

@Data
public class AddressDto {

    private String city;

    private String street;

    private Long number ;

    private String zipCode ;
}
