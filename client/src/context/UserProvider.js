import * as React from "react";
import FullPageSpinner from "../components/FullPageSpinner";
import { fetchData } from "../apiUtils";
import { USER } from "../settings";

const UserContext = React.createContext(null);

const useUser = () => React.useContext(UserContext);

function UserProvider({ children }) {
  const [user, setUser] = React.useState(null);
  const [isStillValidationgToken, setIsStillValidationgToken] =
    React.useState(true);

  // Do validation of token
  React.useEffect(() => {
    if (user !== null) return;
    fetchData(USER.VALIDATE_TOKEN)
      .then(data => setUser({ username: data.username }))
      .finally(() => setIsStillValidationgToken(false));
  }, [user]);

  if (isStillValidationgToken) return <FullPageSpinner />;

  const values = {
    user,
    setUser,
  };

  return <UserContext.Provider value={values}>{children}</UserContext.Provider>;
}

export { UserProvider, useUser };
