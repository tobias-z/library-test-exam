import * as React from "react";
import { Button, Card } from "react-bootstrap";
import { fetchData, handleError, https } from "../apiUtils";
import { useAllert } from "../context/AllertProvider";
import { useLibrary } from "../context/LibraryProvider";
import { useUser } from "../context/UserProvider";
import { BOOKS, LIBRARY } from "../settings";
import EditBook from "./EditBook";

function WithAdmin({ children }) {
  const { user } = useUser();
  const isAdmin = user.roles.find(role => role.roleName === "admin");
  if (isAdmin) return children;
  return null;
}

function Book({ book }) {
  const { user, run } = useUser();
  const { findLibrary } = useLibrary();
  const { showLoan } = useAllert();
  const findLoan = () => user.loans.find(loan => loan.book.isbn === book.isbn);
  const [isLoaned, setIsLoaned] = React.useState(() => {
    const foundLoan = findLoan();
    return foundLoan != null;
  });
  const [error, setError] = React.useState(null);

  async function loanBook() {
    try {
      const loan = await fetchData(BOOKS.LOAN(book.isbn), https.POST);
      await run();
      showLoan(loan);
    } catch (err) {
      handleError(err, setError);
    }
  }

  async function deleteBook() {
    try {
      await fetchData(LIBRARY.DELETE_BOOK(book.isbn), https.DELETE);
      await findLibrary();
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
          <div className="d-flex" style={{ gap: "10px" }}>
            {error && (
              <Card.Text className="text-danger">{error.message}</Card.Text>
            )}
            {!isLoaned ? (
              <Button onClick={() => loanBook()} variant="primary">
                Loan book
              </Button>
            ) : (
              <div>
                <Card.Text>
                  Return before: {new Date(findLoan().dueTo).toLocaleString()}
                </Card.Text>
                <Button onClick={() => alert("TODO")} variant="secondary">
                  Return book
                </Button>
              </div>
            )}
            <WithAdmin>
              <EditBook book={book} />
              <Button onClick={deleteBook} variant="danger">
                Delete
              </Button>
            </WithAdmin>
          </div>
        </Card.Body>
      </Card>
    </>
  );
}

export default Book;
