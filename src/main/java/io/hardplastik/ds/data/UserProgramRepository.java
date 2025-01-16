package io.hardplastik.ds.data;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.hardplastik.ds.model.UserProgram;
import io.hardplastik.ds.model.projections.UserProgramProjection;

@Repository
public interface UserProgramRepository extends JpaRepository<UserProgram, UUID> {
    

    List<UserProgramProjection> findByUserIdOrderByEnrollDatetimeDesc(UUID userId);

}
