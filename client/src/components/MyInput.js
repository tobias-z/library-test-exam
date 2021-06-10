import * as React from "react";
import { Form } from "react-bootstrap";

function MyInput({ id, label, ...props }) {
  return (
    <Form.Group controlId={id}>
      <Form.Label>{label && label}</Form.Label>
      <Form.Control {...props} />
    </Form.Group>
  );
}

export default MyInput;
