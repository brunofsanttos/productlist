package com.bs.listadeprodutos.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

import static com.bs.listadeprodutos.catalog.ErrorCatalog.OBRIGATORIO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class CompanyDto {
    private UUID idCompany;
    @NotBlank(message = OBRIGATORIO)
    private String corporateName;
    @NotBlank(message = OBRIGATORIO)
    private String fantasyName;
    @NotBlank(message = OBRIGATORIO)
    private String cnpj;
    @NotBlank(message = OBRIGATORIO)
    private String email;
}
