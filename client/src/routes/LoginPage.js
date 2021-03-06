import { Button, Form } from "react-bootstrap";
import React from "react";
import CenteredContainer from "../components/CenteredContainer";
import { useAuth } from "../context/AuthProvider";

const initialValues = {
  username: "",
  password: "",
};

function LoginPage() {
  const [loginCredentials, setLoginCredentials] = React.useState(initialValues);
  const [serverError, setServerError] = React.useState(null);
  const { login } = useAuth();

  function handleSubmit(event) {
    event.preventDefault();
    login(loginCredentials, setServerError);
    setLoginCredentials(initialValues);
  }

  function handleChange(event) {
    setLoginCredentials({
      ...loginCredentials,
      [event.target.name]: event.target.value,
    });
  }

  return (
    <CenteredContainer>
      <h1>Sign in</h1>
      <Form style={{ width: "400px" }} onSubmit={handleSubmit}>
        {serverError ? (
          <h3 className="text-danger">{serverError.message}</h3>
        ) : null}
        <Form.Group controlId="username">
          <Form.Label>Username</Form.Label>
          <Form.Control
            type="text"
            name="username"
            value={loginCredentials.username}
            onChange={handleChange}
            placeholder="Enter username"
          />
        </Form.Group>

        <Form.Group controlId="formBasicPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            name="password"
            value={loginCredentials.password}
            onChange={handleChange}
            placeholder="Enter password"
          />
        </Form.Group>
        <Button block type="submit">
          Sign in
        </Button>
      </Form>
    </CenteredContainer>
  );
}

export default LoginPage;
