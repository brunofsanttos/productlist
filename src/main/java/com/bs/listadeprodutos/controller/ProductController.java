package com.bs.listadeprodutos.controller;

import com.bs.listadeprodutos.dto.ProductDto;
import com.bs.listadeprodutos.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import static com.bs.listadeprodutos.catalog.ErrorCatalog.*;

@Controller
@RequestMapping(value = "/api/v1/listproduct/product")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity saveProduct(@Valid @RequestBody ProductDto productDto) {
        try {
            return ResponseEntity.status(201).body(productService.save(productDto));
        } catch (Exception error) {
            log.error(error.getMessage());

            switch (error.getMessage()){
                case EMPRESA_NAO_EXISTE:
                case NAO_ENCONTRADO:
                case NAO_PERTENCE:
                case ERRO_ID:
                case DESCRIPTION:
                case ID_COMPANY:
                    return ResponseEntity.status(400).body(error.getMessage());
                default:
                    return ResponseEntity.status(500).body(ERRO_INTERNO);
            }
        }
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public ResponseEntity finById(
            @RequestParam(name = "idProduct") String idProduct,
            @RequestParam(name = "idCompany") String idCompany ) {
        try {
            return ResponseEntity.status(200).body(productService.findById(idProduct, idCompany));
        } catch (Exception error) {
            log.error(error.getMessage());

            switch (error.getMessage()){
                case EMPRESA_NAO_EXISTE:
                case NAO_ENCONTRADO:
                case NAO_PERTENCE:
                case ERRO_ID:
                case DESCRIPTION:
                case ID_COMPANY:
                    return ResponseEntity.status(400).body(error.getMessage());
                default:
                    return ResponseEntity.status(500).body(ERRO_INTERNO);
            }
        }
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity finByAll(@RequestParam(name = "idCompany") String idCompany ) {
        try {
            return ResponseEntity.status(200).body(productService.findAll(idCompany));
        } catch (Exception error) {
            log.error(error.getMessage());

            switch (error.getMessage()){
                case EMPRESA_NAO_EXISTE:
                case NAO_ENCONTRADO:
                case NAO_PERTENCE:
                case ERRO_ID:
                case DESCRIPTION:
                case ID_COMPANY:
                    return ResponseEntity.status(400).body(error.getMessage());
                default:
                    return ResponseEntity.status(500).body(ERRO_INTERNO);
            }
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity delete(
            @RequestParam(name = "idProduct") String idProduct,
            @RequestParam(name = "idCompany") String idCompany) {
        try {
            return ResponseEntity.status(200).body(productService.delete(idProduct, idCompany));
        } catch (Exception error) {
            log.error(error.getMessage());

            switch (error.getMessage()){
                case EMPRESA_NAO_EXISTE:
                case NAO_ENCONTRADO:
                case NAO_PERTENCE:
                case ERRO_ID:
                case DESCRIPTION:
                case ID_COMPANY:
                    return ResponseEntity.status(400).body(error.getMessage());
                default:
                    return ResponseEntity.status(500).body(ERRO_INTERNO);
            }
        }
    }
}
