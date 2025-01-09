package io.hardplastik.ds.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.hardplastik.ds.model.catalogs.CatRoles;

@Repository
public interface CatRolesRepository extends JpaRepository<CatRoles, String> {
    
}
