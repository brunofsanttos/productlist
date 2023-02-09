package com.bs.listadeprodutos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ListadeprodutosApplication {
	public static void main(String[] args) {
		SpringApplication.run(ListadeprodutosApplication.class, args);
		log.info("[LISTA DE PRODUTOS] - ONLINE");
	}
}
