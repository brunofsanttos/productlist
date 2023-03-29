package com.bs.listadeprodutos.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "company")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCompany;
    private String corporateName;
    private String fantasyName;
    @Column(unique = true)
    private String cnpj;
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<ProductEntity> productList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<PermissionEntity> permissions;
}
