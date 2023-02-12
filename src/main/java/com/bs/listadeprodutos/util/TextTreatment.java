package com.bs.listadeprodutos.util;

import org.springframework.stereotype.Component;

@Component
public class TextTreatment {
    public String CpfCnpjTreatment(String texto){
        texto = texto.replace(".", "");
        texto = texto.replace("-", "");
        texto = texto.replace("/", "");

        return texto;
    }
}
