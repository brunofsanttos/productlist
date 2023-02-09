package com.bs.listadeprodutos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/api/v1/listproduct/user")
public class UsuarioController {
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> salvarUsaurio(){
        return ResponseEntity.status(200).body("ok");
    }
}
