package com.vivo.orders.orders.model;


import com.vivo.orders.orders.dto.RatingDto;
import lombok.Data;

@Data
public class ProductsDto {


    private Long id ;

    private float price ;

    private String description ;

    private String category ;

    private String image ;

    private RatingDto rating;
}
