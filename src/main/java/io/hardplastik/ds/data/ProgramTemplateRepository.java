package io.hardplastik.ds.data;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.hardplastik.ds.model.ProgramTemplate;

@Repository
public interface ProgramTemplateRepository extends JpaRepository<ProgramTemplate, UUID> {
    
}