package io.hardplastik.ds.model.catalogs;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cat_exercise")
@EqualsAndHashCode(of = "id")
public class CatExercise {

    @Id
    @UuidGenerator
    @Column(name = "exercise_id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "media_url")
    private String mediaUrl;

    @JoinColumn(name = "exercise_id")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<CatExerciseMuscleGroup> groups;
    
    public CatExercise(UUID id) {
        this.id = id;
    }

}
