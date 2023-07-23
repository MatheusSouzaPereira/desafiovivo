package com.vivo.orders.orders.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;



@Data
@Entity
@Table
public class ResultDto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    @JsonInclude()
    private UUID id ;

    @Column(name = "id_usuario")
    private Long userId;

    @Column(name = "status")
    private String status ;

    @Column(name = "preco_total")
    private float totalPrice ;

    @JsonInclude()
    @Column(name = "itens")
    @OneToMany(targetEntity = ItemsDto.class, cascade = CascadeType.ALL)
    private List<ItemsDto> products ;


}
