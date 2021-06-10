package security;

import dtos.RoleDTO;
import entities.User;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserPrincipal implements Principal {

  private String username;
  private List<String> rolesAsString;
  private List<RoleDTO> roles;

  /* Create a UserPrincipal, given the Entity class User*/
  public UserPrincipal(User user) {
    this.username = user.getUserName();
    this.rolesAsString = user.getRolesAsStrings();
  }

  public UserPrincipal(String username, String[] rolesAsString, List<RoleDTO> roles) {
    super();
    this.username = username;
    this.rolesAsString = Arrays.asList(rolesAsString);
    this.roles = roles;
  }

  @Override
  public String getName() {
    return username;
  }

  public List<RoleDTO> getRoles() {
    return roles;
  }

  public boolean isUserInRole(String role) {
    return this.rolesAsString.contains(role);
  }
}