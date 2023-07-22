package com.vivo.orders.orders.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table
@Data
@Entity
public class ItemsDto {
    @Id
    @Column(name = "idProduto")
    private Long id ;

    @Column(name = "preco")
    private float price ;

    @Column(name = "valor")
    private Long amount ;

    @Column(name = "valor_parcial")
    private float partialAmount ;
}
