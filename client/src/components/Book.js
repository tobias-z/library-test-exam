import * as React from "react";
import { Button, Card } from "react-bootstrap";
import { fetchData, handleError, https } from "../apiUtils";
import { useAllert } from "../context/AllertProvider";
import { useUser } from "../context/UserProvider";
import { BOOKS } from "../settings";

function Book({ book }) {
  const { user } = useUser();
  const { showLoan } = useAllert();
  const [isLoaned, setIsLoaned] = React.useState(false);
  const [error, setError] = React.useState(null);

  React.useEffect(() => {
    console.log(isLoaned);
  }, [isLoaned]);

  async function loanBook() {
    try {
      const loan = await fetchData(BOOKS.LOAN(book.isbn), https.POST);
      showLoan(loan);
      setIsLoaned(true);
    } catch (err) {
      handleError(err, setError);
    }
  }

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
          {error && (
            <Card.Text className="text-danger">{error.message}</Card.Text>
          )}
          {!isLoaned ? (
            <Button onClick={() => loanBook()} variant="primary">
              Loan book
            </Button>
          ) : (
            <Button onClick={() => alert("TODO")} variant="danger">
              Remove from loans
            </Button>
          )}
        </Card.Body>
      </Card>
    </>
  );
}

export default Book;
