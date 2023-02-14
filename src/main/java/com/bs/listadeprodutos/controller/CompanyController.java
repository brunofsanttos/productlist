package com.bs.listadeprodutos.controller;

import com.bs.listadeprodutos.dto.CompanyDto;
import com.bs.listadeprodutos.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import static com.bs.listadeprodutos.catalog.ErrorCatalog.*;

@Controller
@RequestMapping(value = "/api/v1/listproduct/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity saveCompany(@RequestBody CompanyDto companyDto) {
        try {
            return ResponseEntity.status(200).body(companyService.save(companyDto));
        } catch (Exception error) {
            if (error.getMessage() == ERRO_EMAIL || error.getMessage() == ERRO_NO_CNPJ || error.getMessage() == ERRO_EMAIL || error.getMessage() == ERRO_ID) {
                return ResponseEntity.status(400).body(error.getCause());
            }

            if(error.getMessage().equals(CADASTRO_EXISTENTE)){
                return ResponseEntity.status(401).body(CADASTRO_EXISTENTE);
            }

            return ResponseEntity.status(500).body(ERRO_INTERNO);
        }
    }

    //Todo: tratar erros
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public ResponseEntity findById(@RequestParam(name = "idCompany") String idCompany){
        try{
            return ResponseEntity.status(200).body(companyService.findById(idCompany));
        }catch (Exception error){
            return ResponseEntity.status(500).body(ERRO_INTERNO);
        }
    }
}
