package com.vivo.orders.orders.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class ResultDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private String id ;

    @Column(name = "id_usuario", updatable = false)
    private Long userId;

    @Column(name = "status", updatable = false)
    private String status ;

    @Column(name = "preco_total", updatable = false)
    private float totalPrice ;

    @OneToMany
    @Column(name = "itens", updatable = false)
    private List<ItemsDto> products ;

}
