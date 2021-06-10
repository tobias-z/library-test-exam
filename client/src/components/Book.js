import * as React from "react";
import { Button, Card } from "react-bootstrap";

function Book({ book }) {
  return (
    <>
      <Card style={{ width: "20rem" }}>
        <Card.Img
          variant="top"
          height="300"
          src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fimages.thenile.io%2Fr1000%2F9780747554561.jpg&f=1&nofb=1"
        />
        <Card.Body>
          <Card.Title>{book.authors.map(author => `${author} `)}</Card.Title>
          <Card.Subtitle>{book.title}</Card.Subtitle>
          <Card.Text>{book.description}</Card.Text>
          <Card.Text className="text-muted">
            Published by {book.publisher} in {book.publishYear}
          </Card.Text>
          <hr />
          <Button onClick={() => alert("TODO")} variant="primary">
            Loan book
          </Button>
        </Card.Body>
      </Card>
    </>
  );
}

export default Book;
