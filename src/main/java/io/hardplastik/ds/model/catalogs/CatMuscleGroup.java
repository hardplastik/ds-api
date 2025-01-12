package io.hardplastik.ds.model.catalogs;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

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
@Table(name = "cat_muscle_group")
public class CatMuscleGroup {
  
  @Id
  @Column(name = "muscle_group_id")
  @UuidGenerator
  private UUID id;

  private String name;

}
