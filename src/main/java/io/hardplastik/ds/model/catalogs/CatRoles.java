package io.hardplastik.ds.model.catalogs;

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
@Table(name = "cat_roles")
@EqualsAndHashCode(of = "id")
public class CatRoles {

    @Id
    @Column(name = "role_id")
    private UUID id;
    
    private String name;
    
}
