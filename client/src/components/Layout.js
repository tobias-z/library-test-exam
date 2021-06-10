import * as React from "react";
import AuthenticatedHeader from "./AuthenticatedHeader";

function Layout({ children }) {
  return (
    <>
      <AuthenticatedHeader />
      <main className="container mt-3">{children}</main>
    </>
  );
}

export default Layout;
