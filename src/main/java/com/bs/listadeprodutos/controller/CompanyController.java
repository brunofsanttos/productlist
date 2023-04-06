package com.bs.listadeprodutos.controller;

import com.bs.listadeprodutos.dto.CompanyDto;
import com.bs.listadeprodutos.dto.StandardReturn;
import com.bs.listadeprodutos.service.CompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import static com.bs.listadeprodutos.catalog.ErrorCatalog.*;

@RestController
@Slf4j
@RequestMapping(value = "/api/v1/listproduct/company")
@Tag(name = "Company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @PostMapping(value = "/save")
    public ResponseEntity<StandardReturn> saveCompany(@Valid @RequestBody CompanyDto companyDto) {
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
                    return ResponseEntity.status(400).body(new StandardReturn(error.getMessage()));
                default:
                    return ResponseEntity.status(500).body(new StandardReturn(ERRO_INTERNO));
            }
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<StandardReturn> update(@RequestBody CompanyDto companyDto){
        try{
            return ResponseEntity.status(201).body(companyService.save(companyDto));
        }catch (Exception error){
            log.error(error.getMessage());
            return ResponseEntity.status(500).body(new StandardReturn(ERRO_INTERNO));
        }
    }


    @GetMapping(value = "/findById")
    public ResponseEntity<StandardReturn> findById(@RequestParam(name = "idCompany") String idCompany){
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
                    return ResponseEntity.status(400).body(new StandardReturn(error.getMessage()));
                default:
                    return ResponseEntity.status(500).body(new StandardReturn(ERRO_INTERNO));
            }
        }
    }


    @GetMapping(value = "/findByCnpj")
    public ResponseEntity<StandardReturn> findByCnph(@RequestParam(name = "cnpj") String cnpj){
        try{
            return ResponseEntity.status(200).body(companyService.findByCnpj(cnpj));
        }catch (Exception error){
            switch (error.getMessage()){
                case ERRO_NO_CNPJ:
                    return ResponseEntity.status(400).body(new StandardReturn(error.getMessage()));
                default:
                    return ResponseEntity.status(500).body(new StandardReturn(ERRO_INTERNO));
            }
        }
    }
}
