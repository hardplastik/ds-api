package io.hardplastik.ds.data;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.hardplastik.ds.model.UserProgram;

@Repository
public interface UserProgramRepository extends JpaRepository<UserProgram, UUID> {
    

    List<UserProgram> findByUserIdOrderByEnrollDatetimeDesc(UUID userId);

}
