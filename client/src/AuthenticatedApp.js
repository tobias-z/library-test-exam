import * as React from "react";
import { Route, Switch } from "react-router-dom";
import { AllertProvider } from "./context/AllertProvider";
import { LibraryProvider } from "./context/LibraryProvider";
import NoMatch from "./routes/404";
import HomePage from "./routes/HomePage";

function AuthenticatedApp() {
  return (
    <>
      <LibraryProvider>
        <AllertProvider>
          <Switch>
            <Route path="/" exact component={HomePage} />
            <Route path="/" component={NoMatch} />
          </Switch>
        </AllertProvider>
      </LibraryProvider>
    </>
  );
}

export default AuthenticatedApp;
