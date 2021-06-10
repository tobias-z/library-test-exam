package dtos;

import entities.Role;
import java.util.List;
import java.util.stream.Collectors;

public class RoleDTO {

    private String roleName;

    public RoleDTO(String roleName) {
        this.roleName = roleName;
    }

    public RoleDTO(Role role) {
        this.roleName = role.getRoleName();
    }

    public static List<RoleDTO> getRoleDTOSFromRoles(List<Role> roles) {
        return roles.stream().map(role -> new RoleDTO(role)).collect(Collectors.toList());
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
