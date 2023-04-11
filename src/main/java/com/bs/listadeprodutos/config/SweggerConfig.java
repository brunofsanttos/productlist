package com.bs.listadeprodutos.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition
@Configuration
public class SweggerConfig {
    @Bean
    public OpenAPI custumerOpenApi(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Lista de produtos")
                        .description("Apis que servem a lista de produtos"))
                .paths(new Paths());
    }
}
