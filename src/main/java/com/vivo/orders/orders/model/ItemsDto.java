package com.vivo.orders.orders.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.UUID;


@Data
@Entity
@Generated
@Table(name = "items")
public class ItemsDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produtc_sa")
    private Long id;

    @Column(name = "preco_produto")
    private float price;

    @Column(name = "valor_produto")
    private Long amount;

    @Column(name = "valor_parcial_produto")
    private float partialAmount;
}
