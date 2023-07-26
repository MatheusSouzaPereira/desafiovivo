package com.vivo.orders.orders.utils;

import com.vivo.orders.orders.dto.AddressDto;
import com.vivo.orders.orders.dto.NameDto;
import com.vivo.orders.orders.dto.ProductsDto;
import com.vivo.orders.orders.dto.UserDto;
import com.vivo.orders.orders.model.ItemsDto;
import com.vivo.orders.orders.model.ResultDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class Utils {

    public static final String GEOLOCATION_LAT = "-37.3159";
    public static final String GEOLOCATION_LONG = "81.1496";

    public static final String CITY = "kilcoole";

    public static final String STREET = "new road";
    public static final Long NUMBER = 7682L;
    public static final String ZIPCODE = "12926-3874";
    public static final String EMAIL = "john@gmail.com";
    public static final String USERNAME = "johnd";
    public static final String PASSWORD = "m38rmF$";
    public static final String NAME_FIRSTNAME = "john";
    public static final String NAME_LASTNAME = "doe";
    public static final String PHONE = "1-570-236-7033";
    public static final Long USERID_VALID = 1l;
    public static final Long USERID_INVALID = 1000L;
    public static final String _v = "81.1496";

    public static final String RESULTDTO_STATUS_PENDING = "PENDING";

    public static final float PRICE = 1009.95f;


    public static UserDto usingUserExist(){
        UserDto userDto = new UserDto();
        NameDto nameDto = new NameDto();
        AddressDto addressDto = new AddressDto();
        ResultDto resultDto = new ResultDto();

        resultDto.setId(UUID.randomUUID());

        userDto.setPhone(PHONE);
        userDto.setEmail(EMAIL);
        userDto.setUsername(USERNAME);
        userDto.setPassword(PASSWORD);
        userDto.setV(_v);

        nameDto.setFirstName(NAME_FIRSTNAME);
        nameDto.setLastName(NAME_LASTNAME);

        addressDto.setCity(CITY);
        addressDto.setStreet(STREET);
        addressDto.setNumber(NUMBER);
        addressDto.setZipCode(ZIPCODE);

        userDto.setId(USERID_VALID);
        userDto.setName(nameDto);
        userDto.setAddress(addressDto);

        return userDto;

    }

    public static ResultDto usingResultDtoStatusPending(){
        UserDto userDto = new UserDto();
        ResultDto resultDto = new ResultDto();

        userDto.setId(USERID_VALID);

        resultDto.setUserId(USERID_VALID);
        resultDto.setStatus(RESULTDTO_STATUS_PENDING);
        resultDto.setId(UUID.randomUUID());
        return resultDto;
    }

    public static List<ItemsDto>  usingListDto(){
        List<ItemsDto> itemDtoList = new ArrayList<>();
        ItemsDto itemsDto = new ItemsDto();
        ResultDto resultDto = new ResultDto();

        resultDto.setUserId(USERID_VALID);
        resultDto.setStatus(RESULTDTO_STATUS_PENDING);
        resultDto.setId(UUID.randomUUID());
        itemsDto.setId(1L);

        itemDtoList.add(itemsDto);


        return itemDtoList;
    }


    public static List<ItemsDto>  usingListItemsDto(){
        List<ItemsDto> itemsDtoList = new ArrayList<>();
        ItemsDto itemsDto = new ItemsDto();
        itemsDto.setId(1l);
        itemsDto.setPrice(PRICE);
        itemsDto.setAmount(1l);
        itemsDto.setPartialAmount(itemsDto.getPrice() + PRICE);

        itemsDtoList.add(itemsDto);

        itemsDto.setId(1l);
        itemsDto.setPrice(PRICE);
        itemsDto.setAmount(1l);
        itemsDto.setPartialAmount(itemsDto.getPrice() + PRICE);

        itemsDtoList.add(itemsDto);

        return itemsDtoList;
    }
}
