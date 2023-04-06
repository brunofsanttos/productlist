package com.bs.listadeprodutos.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class SweggerConfig {
    @Bean
   public OpenAPI customOpenApi(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Lista de Produtos")
                        .description("Serviço que serve uma lista de produtos mantidos pela empresa"));
    }
}
