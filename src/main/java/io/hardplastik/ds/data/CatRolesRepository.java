package io.hardplastik.ds.data;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.hardplastik.ds.model.catalogs.CatRole;

@Repository
public interface CatRolesRepository extends JpaRepository<CatRole, UUID> {
    
}
