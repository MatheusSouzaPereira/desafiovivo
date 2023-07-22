package com.vivo.orders.orders.dto;

import lombok.Data;

@Data
public class UserDto {

    public Long id ;

    public String email ;

    private String username;

    private String password ;

    private NameDto name ;
    private AddressDto address;

    private String phone ;

    private String v ;
}
