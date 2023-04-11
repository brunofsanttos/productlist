package com.bs.listadeprodutos.controller;

import com.bs.listadeprodutos.dto.ProductDto;
import com.bs.listadeprodutos.dto.StandardReturn;
import com.bs.listadeprodutos.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.bs.listadeprodutos.catalog.ErrorCatalog.*;

@Controller
@Slf4j
@RequestMapping(value = "/api/v1/listproduct/product")
@Tag(name = "Products")
public class ProductController {
    @Autowired
    private ProductService productService;


    @PostMapping(value = "/save")
    public ResponseEntity<StandardReturn> saveProduct(@Valid @RequestBody ProductDto productDto) {
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
                    return ResponseEntity.status(400).body(new StandardReturn(error.getMessage()));
                default:
                    return ResponseEntity.status(500).body(new StandardReturn(ERRO_INTERNO));
            }
        }
    }

    @GetMapping(value = "/findById")
    public ResponseEntity<StandardReturn> finById(
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
                    return ResponseEntity.status(400).body(new StandardReturn(error.getMessage()));
                default:
                    return ResponseEntity.status(500).body(new StandardReturn(ERRO_INTERNO));
            }
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<StandardReturn> finByAll(@RequestParam(name = "idCompany") String idCompany ) {
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
                    return ResponseEntity.status(400).body(new StandardReturn(error.getMessage()));
                default:
                    return ResponseEntity.status(500).body(new StandardReturn(ERRO_INTERNO));
            }
        }
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<StandardReturn> delete(
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
                    return ResponseEntity.status(400).body(new StandardReturn(error.getMessage()));
                default:
                    return ResponseEntity.status(500).body(new StandardReturn(ERRO_INTERNO));
            }
        }
    }
}
