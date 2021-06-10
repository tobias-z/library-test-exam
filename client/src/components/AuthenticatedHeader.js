import * as React from "react";
import { Container, Nav, Navbar } from "react-bootstrap";
import { LinkContainer } from "react-router-bootstrap";
import { useAuth } from "../context/AuthProvider";
import { useUser } from "../context/UserProvider";
import { WithAdmin } from "./Book";

function AuthenticatedHeader() {
  const { user } = useUser();
  const { logout } = useAuth();

  return (
    <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
      <Container>
        <LinkContainer to="/">
          <Navbar.Brand>The Library</Navbar.Brand>
        </LinkContainer>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="mr-auto">
            <LinkContainer exact to="/">
              <Nav.Link>Home</Nav.Link>
            </LinkContainer>
            <WithAdmin>
              <LinkContainer exact to="/add-book">
                <Nav.Link>Add Book</Nav.Link>
              </LinkContainer>
            </WithAdmin>
          </Nav>
          <Nav>
            <Navbar.Text>Signed in as: {user.username}</Navbar.Text>
            <LinkContainer to="/">
              <Nav.Link onClick={() => logout()}>Log out</Nav.Link>
            </LinkContainer>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default AuthenticatedHeader;
