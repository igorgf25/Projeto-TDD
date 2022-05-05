package com.gft.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotasSaque {
    private int notas100;

    private int notas50;

    private int notas20;

    private int notas10;
}
