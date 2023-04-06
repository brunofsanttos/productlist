package com.bs.listadeprodutos.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InformetionExecption extends Exception{
    private final String menssagem;
}
