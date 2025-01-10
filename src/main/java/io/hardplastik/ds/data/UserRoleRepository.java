package io.hardplastik.ds.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.hardplastik.ds.model.AccountRole;
import io.hardplastik.ds.model.Serializable.AccountRolePk;

@Repository
public interface UserRoleRepository extends JpaRepository<AccountRole, AccountRolePk> {
    
}
