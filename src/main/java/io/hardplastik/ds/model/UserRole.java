package io.hardplastik.ds.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import io.hardplastik.ds.model.Serializable.UserRolePk;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
@Table(name = "user_role")
@EqualsAndHashCode(of = "pk")
public class UserRole {

    @EmbeddedId
    @JsonUnwrapped
    private UserRolePk pk;

}