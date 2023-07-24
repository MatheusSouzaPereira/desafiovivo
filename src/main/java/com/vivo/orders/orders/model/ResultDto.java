package com.vivo.orders.orders.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.List;
import java.util.UUID;



@Data
@Entity
@Table
@Generated
public class ResultDto {

    @Id
    @JsonInclude()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id ;

    @Column(name = "id_usuario")
    private Long userId;


    @Column(name = "status")
    private String status ;

    @Column(name = "preco_total")
    private float totalPrice ;

    @JsonInclude()
    @Column(name = "itens")
    @Transient
    private List<ItemsDto> items ;


}
