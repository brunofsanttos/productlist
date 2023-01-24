package com.bs.listadeprodutos.persistence.repository;

import com.bs.listadeprodutos.persistence.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity, UUID> {
    boolean existsByCnpj(String cnpj);
    boolean existsByEmail(String email);
    boolean existsByRazaoSocial(String razaoSocial);
}
