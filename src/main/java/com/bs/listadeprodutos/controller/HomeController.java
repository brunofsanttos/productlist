package com.bs.listadeprodutos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
@RequestMapping(value = "/api/V1/listproduct")
public class HomeController {
    private final Date dataAtual = new Date();

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public ResponseEntity<String> homeGetStatus(){
        return ResponseEntity.status(200).body("/api/V1/listproduct... ONLINE... " + dataAtual.toString());
    }
}
