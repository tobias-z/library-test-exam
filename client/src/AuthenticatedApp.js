import * as React from "react";
import { Route, Switch } from "react-router-dom";
import NoMatch from "./routes/404";
import HomePage from "./routes/HomePage";

function AuthenticatedApp() {
  return (
    <>
      <Switch>
        <Route path="/" exact component={HomePage} />
        <Route path="/" component={NoMatch} />
      </Switch>
    </>
  );
}

export default AuthenticatedApp;
