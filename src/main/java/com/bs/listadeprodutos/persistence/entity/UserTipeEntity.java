package com.bs.listadeprodutos.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "user_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTipeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUserTipe;

    private String descripton ;

    @ManyToMany
    @JoinTable(
            name = "user_tipe_permissions",
            joinColumns = @JoinColumn(name = "user_tipe_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<PermissionEntity> permissions;
}
