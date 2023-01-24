package com.bs.listadeprodutos.service;

import com.bs.listadeprodutos.dto.EmpresaDto;
import com.bs.listadeprodutos.dto.RetornoPadraoDto;

public interface EmpresaService {
    RetornoPadraoDto save(EmpresaDto empresaDto) throws Exception;
    RetornoPadraoDto consultaPorId(String id)throws Exception;
}
