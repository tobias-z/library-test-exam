import * as React from "react";
import { Switch, Route } from "react-router-dom";
import NoMatch from "./routes/404";
import LoginPage from "./routes/LoginPage";

function UnauthenticatedApp() {
  return (
    <>
      <Switch>
        <Route path="/">
          <LoginPage />
        </Route>
        <Route path="/" component={NoMatch} />
      </Switch>
    </>
  );
}

export default UnauthenticatedApp;
