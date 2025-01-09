package io.hardplastik.ds.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.hardplastik.ds.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    
}
