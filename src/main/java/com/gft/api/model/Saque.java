package com.gft.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "tb_saque")
@AllArgsConstructor
@NoArgsConstructor
public class Saque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuario_id;

    private Double valor;

    private LocalTime horario;

    public Saque(Double valor) {
        this.valor = valor;
    }
}
