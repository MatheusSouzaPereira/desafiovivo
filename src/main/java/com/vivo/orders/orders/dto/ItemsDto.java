package com.vivo.orders.orders.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.UUID;


@Data
@Entity
@JsonInclude()
@Table(name = "items")
public class ItemsDto {

    @Id
    @Column(name = "id_produtc_sa")
    private Long id ;

    @JsonInclude()
    @Column(name = "preco_produto")
    private float price ;

    @Column(name = "valor_produto")
    private Long amount ;

    @Column(name = "valor_parcial_produto")
    private float partialAmount ;
}
