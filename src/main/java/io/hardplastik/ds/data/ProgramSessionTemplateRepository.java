package io.hardplastik.ds.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.hardplastik.ds.model.ProgramSessionTemplate;

@Repository
public interface ProgramSessionTemplateRepository extends JpaRepository<ProgramSessionTemplate, String> {
    
}
