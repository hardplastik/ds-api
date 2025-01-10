package io.hardplastik.ds.controller.command;

import io.hardplastik.ds.model.catalogs.CatRoles;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CatRolesCommand {
            
    private String name;

    public CatRoles toEntity() {
        CatRoles roles = new CatRoles();
        roles.setName(name);
        return roles;
    }
}
