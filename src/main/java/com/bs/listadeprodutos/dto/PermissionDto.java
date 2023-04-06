package com.bs.listadeprodutos.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonSerialize
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PermissionDto {
    private String idPermission;
    private String idCompany;
    private String description;
    private Boolean status;
}
