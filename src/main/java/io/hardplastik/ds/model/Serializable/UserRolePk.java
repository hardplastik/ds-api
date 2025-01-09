package io.hardplastik.ds.model.Serializable;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode(of = {"userId", "roleId"})
public class UserRolePk implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @JoinColumn(name = "user_id")
    private String userId;

    @Column(name = "role_id")
    private String roleId;

}
