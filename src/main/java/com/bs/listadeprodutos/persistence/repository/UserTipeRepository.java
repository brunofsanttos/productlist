package com.bs.listadeprodutos.persistence.repository;

import com.bs.listadeprodutos.persistence.entity.UserTipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserTipeRepository extends JpaRepository<UserTipeEntity, UUID> {
}
