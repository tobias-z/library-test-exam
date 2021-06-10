import * as React from "react";
import { useRouteMatch } from "react-router";
import Layout from "../components/Layout";

function NoMatch() {
  const { path } = useRouteMatch();
  return (
    <Layout>
      <h1>No such path found: {path}</h1>
    </Layout>
  );
}

export default NoMatch;
