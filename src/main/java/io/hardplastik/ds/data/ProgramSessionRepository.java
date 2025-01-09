package io.hardplastik.ds.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.hardplastik.ds.model.ProgramSession;

@Repository
public interface ProgramSessionRepository extends JpaRepository<ProgramSession, String> {
    
}
