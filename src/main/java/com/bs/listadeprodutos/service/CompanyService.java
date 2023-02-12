package com.bs.listadeprodutos.service;

import com.bs.listadeprodutos.dto.CompanyDto;
import com.bs.listadeprodutos.dto.StandardReturn;

public interface CompanyService {
    StandardReturn save(CompanyDto empresaDto) throws Exception;
    StandardReturn findById(String id)throws Exception;
}
