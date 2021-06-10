package dtos;

import entities.User;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private String username;
    private List<RoleDTO> roles;
    private List<LoanDTO> loans;

    public UserDTO(String username, List<RoleDTO> roles, List<LoanDTO> loans) {
        this.username = username;
        this.roles = roles;
        this.loans = loans;
    }

    public UserDTO(User user) {
        System.out.println(user);
        this.username = user.getUserName();
        this.roles = RoleDTO.getRoleDTOSFromRoles(user.getRoleList());
        this.loans = LoanDTO.getLoanDTOSFromLoans(user.getLoans());
    }

    public List<String> getRolesAsStrings() {
        if (roles.isEmpty()) {
            return null;
        }
        List<String> rolesAsStrings = new ArrayList<>();
        roles.forEach((role) -> {
            rolesAsStrings.add(role.getRoleName());
        });
        return rolesAsStrings;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public List<LoanDTO> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanDTO> loans) {
        this.loans = loans;
    }
}
