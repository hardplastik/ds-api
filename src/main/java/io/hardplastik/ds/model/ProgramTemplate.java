package io.hardplastik.ds.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "program_template")
public class ProgramTemplate {

    @Id
    private UUID id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 500)
    private String description;
    
}
