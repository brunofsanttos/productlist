package com.bs.listadeprodutos.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@JsonSerialize
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private String idProduct;
    private String idCompany;
    private String description;
    private BigDecimal unitPrice;
}
