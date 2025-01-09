package io.hardplastik.ds.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.hardplastik.ds.model.UserRole;
import io.hardplastik.ds.model.Serializable.UserRolePk;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRolePk> {
    
}
