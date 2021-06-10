import * as React from "react";
import FullPageSpinner from "../components/FullPageSpinner";
import { fetchData } from "../apiUtils";
import { BOOKS, USER } from "../settings";

const UserContext = React.createContext(null);

const useUser = () => React.useContext(UserContext);

function UserProvider({ children }) {
  const [user, setUser] = React.useState(null);
  const [isStillValidationgToken, setIsStillValidationgToken] =
    React.useState(true);

  function run() {
    Promise.all([
      fetchData(USER.VALIDATE_TOKEN),
      fetchData(BOOKS.GET_ALL_FROM_USER),
    ])
      .then(([user, loans]) => {
        setUser({
          username: user.username,
          roles: JSON.parse(user.roles),
          loans: loans,
        });
      })
      .catch(err => {})
      .finally(() => {
        setIsStillValidationgToken(false);
      });
  }

  // Do validation of token
  React.useEffect(() => {
    if (user !== null) return;
    run();
  }, [user]);

  if (isStillValidationgToken) return <FullPageSpinner />;

  const values = {
    user,
    setUser,
    run,
  };

  return <UserContext.Provider value={values}>{children}</UserContext.Provider>;
}

export { UserProvider, useUser };
