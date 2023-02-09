package com.bs.listadeprodutos.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "empresa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idEmpresa;
    private String razaoSocial;
    private String nomeFantasia;
    @Column(unique = true)
    private String cnpj;
    private String email;
}
