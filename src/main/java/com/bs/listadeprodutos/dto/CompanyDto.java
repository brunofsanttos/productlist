package com.bs.listadeprodutos.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class CompanyDto {
    private UUID idCompany;
    private String corporateName;
    private String fantasyName;
    private String cnpj;
    private String email;
}
