package io.hardplastik.ds.controller.command;

import io.hardplastik.ds.model.catalogs.CatRole;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CatRolesCommand {
            
    private String name;

    public CatRole toEntity() {
        CatRole roles = new CatRole();
        roles.setName(name);
        return roles;
    }
}
