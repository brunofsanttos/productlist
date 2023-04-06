package com.bs.listadeprodutos.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Date;

@Controller
@RequestMapping(value = "/api/v1/listproduct")
@Tag(name = "Home")
public class HomeController {
    private final Date dataAtual = new Date();

    @GetMapping(value = "/home")
    public ResponseEntity<String> homeGetStatus(){
        return ResponseEntity.status(200).body("/api/V1/listproduct... ONLINE... " + dataAtual.toString());
    }
}
