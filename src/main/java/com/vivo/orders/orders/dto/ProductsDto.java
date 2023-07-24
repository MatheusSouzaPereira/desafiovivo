package com.vivo.orders.orders.dto;


import com.vivo.orders.orders.dto.RatingDto;
import lombok.Data;
import lombok.Generated;

@Data
@Generated
public class ProductsDto {


    private Long id ;

    private float price ;

    private String description ;

    private String category ;

    private String image ;

    private RatingDto rating;
}
