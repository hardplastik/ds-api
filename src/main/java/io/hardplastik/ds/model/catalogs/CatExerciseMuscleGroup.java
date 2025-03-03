package io.hardplastik.ds.model.catalogs;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@EqualsAndHashCode(of = {"id"})
@Table(name = "cat_exercise_muscle_group")
public class CatExerciseMuscleGroup {
  
  @Id
  @JsonIgnore
  @UuidGenerator
  @Column(name = "cat_exercise_muscle_group_id")
  private UUID id;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "exercise_id")
  private CatExercise exercise;

  @JsonUnwrapped
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "muscle_group_id")
  private CatMuscleGroup group;

  @Column(name = "is_principal_group")
  private Boolean isPrincipal;

}
