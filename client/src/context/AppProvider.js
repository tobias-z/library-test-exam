import * as React from "react";
import { AuthProvider } from "./AuthProvider";
import { UserProvider } from "./UserProvider";

function AppProvider({ children }) {
  return (
    <UserProvider>
      <AuthProvider>{children}</AuthProvider>
    </UserProvider>
  );
}

export default AppProvider;
