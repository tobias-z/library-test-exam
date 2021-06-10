import * as React from "react";
import { Spinner } from "react-bootstrap";
import CenteredContainer from "./CenteredContainer";

function FullPageSpinner() {
  return (
    <CenteredContainer>
      <Spinner animation="border" variant="primary" role="status">
        <span className="sr-only">Loading...</span>
      </Spinner>
    </CenteredContainer>
  );
}

export default FullPageSpinner;
