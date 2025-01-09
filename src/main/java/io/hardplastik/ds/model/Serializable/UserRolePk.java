package io.hardplastik.ds.model.Serializable;

import java.io.Serializable;

import io.hardplastik.ds.model.User;
import io.hardplastik.ds.model.catalogs.CatRoles;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserRolePk implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private CatRoles role;

}
