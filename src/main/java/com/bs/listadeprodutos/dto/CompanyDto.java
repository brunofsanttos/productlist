package com.bs.listadeprodutos.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class CompanyDto {
    private String idEmpresa;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String email;
}
