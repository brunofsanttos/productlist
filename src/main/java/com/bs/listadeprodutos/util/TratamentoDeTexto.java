package com.bs.listadeprodutos.util;

import org.springframework.stereotype.Component;

@Component
public class TratamentoDeTexto {

    public String RemoveCaracterEspecial(String texto){
        texto = texto.replace(".", "");
        texto = texto.replace("-", "");
        texto = texto.replace("/", "");

        return texto;
    }

}
