package com.gft.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "tb_caixa_eletronico")
@AllArgsConstructor
@NoArgsConstructor
public class CaixaEletronico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int notas100;

    private int notas50;

    private int notas20;

    private int notas10;

    private BigDecimal valor_total;

}
