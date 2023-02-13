package com.bs.listadeprodutos.persistence.repository;

import com.bs.listadeprodutos.persistence.entity.CompanyEntity;
import com.bs.listadeprodutos.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    List<ProductEntity> findAllByIdCompany(UUID idCompany);
}
