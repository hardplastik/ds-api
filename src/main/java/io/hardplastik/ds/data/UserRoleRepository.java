package io.hardplastik.ds.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.hardplastik.ds.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {
    
}
