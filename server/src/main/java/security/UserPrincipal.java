package security;

import dtos.LoanDTO;
import entities.User;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserPrincipal implements Principal {

  private String username;
  private List<String> roles = new ArrayList<>();
  private List<LoanDTO> loans;

  /* Create a UserPrincipal, given the Entity class User*/
  public UserPrincipal(User user) {
    this.username = user.getUserName();
    this.roles = user.getRolesAsStrings();
    this.loans = LoanDTO.getLoanDTOSFromLoans(user.getLoans());
  }

  public UserPrincipal(String username, String[] roles, List<LoanDTO> loans) {
    super();
    this.username = username;
    this.roles = Arrays.asList(roles);
    this.loans = loans;
  }

  @Override
  public String getName() {
    return username;
  }

  public List<LoanDTO> getLoans() {
    return loans;
  }

  public void setLoans(List<LoanDTO> loans) {
    this.loans = loans;
  }

  public boolean isUserInRole(String role) {
    return this.roles.contains(role);
  }
}