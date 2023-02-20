package com.bs.listadeprodutos.controller;

import com.bs.listadeprodutos.dto.CompanyDto;
import com.bs.listadeprodutos.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import static com.bs.listadeprodutos.catalog.ErrorCatalog.*;

@RestController
@Slf4j
@RequestMapping(value = "/api/v1/listproduct/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity saveCompany(@Valid @RequestBody CompanyDto companyDto) {
        try {
            return ResponseEntity.status(201).body(companyService.save(companyDto));
        } catch (Exception error) {
            log.error(error.getMessage());

            switch (error.getMessage()){
                case ERRO_NO_CNPJ:
                case ERRO_RAZAO_SOCIAL:
                case ERRO_EMAIL:
                case ERRO_ID:
                case ID_EXISTENTE:
                case CNPJ_CADASTRADO:
                case OBRIGATORIO:
                case DADO_INVALIDO:
                    return ResponseEntity.status(400).body(error.getMessage());
                default:
                    return ResponseEntity.status(500).body(ERRO_INTERNO);
            }
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody CompanyDto companyDto){
        try{
            return ResponseEntity.status(201).body(companyService.save(companyDto));
        }catch (Exception error){
            log.error(error.getMessage());
            return ResponseEntity.status(500).body(ERRO_INTERNO);
        }
    }

    //Todo: tratar erros
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public ResponseEntity findById(@RequestParam(name = "idCompany") String idCompany){
        try{
            return ResponseEntity.status(200).body(companyService.findById(idCompany));
        }catch (Exception error){
            switch (error.getMessage()){
                case ERRO_NO_CNPJ:
                case ERRO_RAZAO_SOCIAL:
                case ERRO_EMAIL:
                case ERRO_ID:
                case ID_EXISTENTE:
                case CNPJ_CADASTRADO:
                case OBRIGATORIO:
                case DADO_INVALIDO:
                    return ResponseEntity.status(400).body(error.getMessage());
                default:
                    return ResponseEntity.status(500).body(ERRO_INTERNO);
            }
        }
    }
}
