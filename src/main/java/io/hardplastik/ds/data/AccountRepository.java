package io.hardplastik.ds.data;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.hardplastik.ds.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    
    @Transactional
    Optional<Account> findByUsername(String username);

}
