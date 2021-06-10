import * as React from "react";
import { Switch, Route } from "react-router-dom";
import UnauthenticatedHeader from "./components/UnauthenticatedHeader";
import NoMatch from "./routes/404";
import HomePage from "./routes/HomePage";
import LoginPage from "./routes/LoginPage";

function UnauthenticatedApp() {
  return (
    <>
      <UnauthenticatedHeader />
      <Switch>
        <Route path="/" exact component={HomePage} />
        <Route path="/login">
          <LoginPage />
        </Route>
        <Route path="/" component={NoMatch} />
      </Switch>
    </>
  );
}

export default UnauthenticatedApp;
