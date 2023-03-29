package com.bs.listadeprodutos.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "permissions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPermission;

    @ManyToOne
    @JoinColumn(name = "id_company")
    private CompanyEntity company;

    @ManyToMany(mappedBy="permissions", cascade = CascadeType.ALL)
    private List<UserTipeEntity> userTipe;

    private String description;
    private Boolean status;
}
