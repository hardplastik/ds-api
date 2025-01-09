package io.hardplastik.ds.data;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.hardplastik.ds.model.UserProgram;

@Repository
public interface UserProgramRepository extends JpaRepository<UserProgram, UUID> {
    
}
