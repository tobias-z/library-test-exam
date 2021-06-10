import * as React from "react";
import { Button, Card } from "react-bootstrap";

function Book({ book }) {
  return (
    <>
      <Card style={{ width: "22rem" }}>
        <Card.Img variant="top" height="300" src={book.image} />
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
