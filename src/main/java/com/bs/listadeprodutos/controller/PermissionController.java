package com.bs.listadeprodutos.controller;

import com.bs.listadeprodutos.dto.PermissionDto;
import com.bs.listadeprodutos.dto.StandardReturn;
import com.bs.listadeprodutos.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.bs.listadeprodutos.catalog.ErrorCatalog.*;

@Controller
@RequestMapping(value = "/api/v1/listproduct/permission")
@Slf4j
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @PostMapping(value = "/save")
    public ResponseEntity<StandardReturn> permissionSave(@Valid @RequestBody PermissionDto permissionDto){
        try {
            return ResponseEntity.status(201).body(permissionService.save(permissionDto));
        } catch (Exception error) {
            log.error(error.getMessage());
            return ResponseEntity.status(500).body(new StandardReturn(ERRO_INTERNO));
        }
    }

    @GetMapping(value = "/findByIdPermission")
    public ResponseEntity<StandardReturn> permissionFindByIdPermission(@Valid @RequestParam(name = "idPermission") String idPermission){
        try {
            return ResponseEntity.status(200).body(permissionService.findByIdPermission(idPermission));
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

    @GetMapping(value = "/findByIdCompany")
    public ResponseEntity<StandardReturn> permissionFindByidCompany(@Valid @RequestParam(name = "idCompany") String idCompany){
        try {
            return ResponseEntity.status(200).body(permissionService.findByIdCompany(idCompany));
        } catch (Exception error) {
            log.error(error.getMessage());

            switch (error.getMessage()){
                case EMPRESA_NAO_EXISTE:
                case NAO_ENCONTRADO:
                case NAO_PERTENCE:
                case ERRO_ID:
                case DESCRIPTION:
                case ID_COMPANY:
                    return ResponseEntity.status(400).body( new StandardReturn(error.getMessage()));
                default:
                    return ResponseEntity.status(500).body(new StandardReturn(ERRO_INTERNO));
            }
        }
    }
}
