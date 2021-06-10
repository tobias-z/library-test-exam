import * as React from "react";
import { useUser } from "./UserProvider";
import * as facade from "../facades/userFacade";
import { useHistory } from "react-router";
import { handleError } from "../apiUtils";

const AuthContext = React.createContext({
  login: async (userCredentials, setError) => {},
  logout: () => {},
});

const useAuth = () => React.useContext(AuthContext);

function AuthProvider({ children }) {
  const { setUser } = useUser();
  const { push } = useHistory();

  async function login(userCredentials, setError) {
    try {
      const user = await facade.login(userCredentials);
      push("/");
      setUser({ username: user.username });
    } catch (error) {
      handleError(error, setError);
    }
  }

  function logout() {
    facade.logout();
    setUser(null);
  }

  const values = {
    login,
    logout,
  };

  return <AuthContext.Provider value={values}>{children}</AuthContext.Provider>;
}

export { AuthProvider, useAuth };
