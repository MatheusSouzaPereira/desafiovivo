package com.vivo.orders.orders.dto;

import com.vivo.orders.orders.dto.AddressDto;
import com.vivo.orders.orders.dto.NameDto;
import lombok.Data;
import lombok.Generated;

@Data
@Generated
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
