package io.hardplastik.ds.model.Serializable;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
@EqualsAndHashCode(of = {"accountId", "roleId"})
public class AccountRolePk implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "account_id")
    private String accountId;

    @Column(name = "role_id")
    private String roleId;

}
