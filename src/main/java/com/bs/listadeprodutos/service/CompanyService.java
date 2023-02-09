package com.bs.listadeprodutos.service;

import com.bs.listadeprodutos.dto.CompanyDto;
import com.bs.listadeprodutos.dto.RetornoPadraoDto;

public interface CompanyService {
    RetornoPadraoDto save(CompanyDto empresaDto) throws Exception;
    RetornoPadraoDto findById(String id)throws Exception;
}
