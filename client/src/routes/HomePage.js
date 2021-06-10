import * as React from "react";
import Book from "../components/Book";
import FullPageSpinner from "../components/FullPageSpinner";
import Layout from "../components/Layout";
import { useLibrary } from "../context/LibraryProvider";
import { useUser } from "../context/UserProvider";
import { Form } from "react-bootstrap";

function HomePage() {
  const { user } = useUser();
  const { library } = useLibrary();
  const [search, setSearch] = React.useState("");

  return (
    <Layout>
      {library ? (
        <>
          <div className="d-flex justify-content-between">
            <h1 className="text-center">
              Welcome to {library.name} {user.username}
            </h1>
            <Form.Group controlId="formSeach">
              <Form.Control
                className="mt-2"
                type="search"
                size="lg"
                value={search}
                onChange={e => setSearch(e.target.value)}
                placeholder="Search for a book"
              />
            </Form.Group>
          </div>
          <hr className="mb-4" />
          <div
            className="d-flex flex-wrap justify-content-center"
            style={{ gap: "20px" }}>
            {library.books
              .filter(book => bookFiltering(book, search))
              .map(book => (
                <Book key={book.isbn} book={book} />
              ))}
          </div>
        </>
      ) : (
        <FullPageSpinner />
      )}
    </Layout>
  );
}

function bookFiltering(book, search) {
  return (
    book.title.toLowerCase().includes(search.toLowerCase()) ||
    book.authors
      .map(a => a)
      .join("")
      .toLowerCase()
      .includes(search.toLowerCase())
  );
}

export default HomePage;
