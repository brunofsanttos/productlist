package com.bs.listadeprodutos.controller;

import com.bs.listadeprodutos.dto.EmpresaDto;
import com.bs.listadeprodutos.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import static com.bs.listadeprodutos.catalog.ErrorCatalog.*;

@Controller
@RequestMapping(value = "/api/v1/listproduct/company")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity salvarEmpresa(@RequestBody EmpresaDto empresaDto) {
        try {
            return ResponseEntity.status(200).body(empresaService.save(empresaDto));
        } catch (Exception erro) {
            if (erro.getMessage() == ERRO_EMAIL || erro.getMessage() == ERRO_NO_CNPJ || erro.getMessage() == ERRO_EMAIL || erro.getMessage() == ERRO_ID) {
                return ResponseEntity.status(400).body(erro.getCause());
            }

            if(erro.getMessage().equals(CADASTRO_EXISTENTE)){
                return ResponseEntity.status(401).body(CADASTRO_EXISTENTE);
            }

            return ResponseEntity.status(500).body(ERRO_INTERNO);
        }
    }
}
