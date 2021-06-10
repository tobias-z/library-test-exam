import * as React from "react";
import { Route, Switch } from "react-router-dom";
import { LibraryProvider } from "./context/LibraryProvider";
import NoMatch from "./routes/404";
import HomePage from "./routes/HomePage";

function AuthenticatedApp() {
  return (
    <>
      <LibraryProvider>
        <Switch>
          <Route path="/" exact component={HomePage} />
          <Route path="/" component={NoMatch} />
        </Switch>
      </LibraryProvider>
    </>
  );
}

export default AuthenticatedApp;
