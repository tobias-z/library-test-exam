import * as React from "react";
import Book from "../components/Book";
import Layout from "../components/Layout";
import { useUser } from "../context/UserProvider";
import { library } from "../mocks";

function HomePage() {
  const { user } = useUser();
  return (
    <Layout>
      <h1 className="text-center">
        Welcome to {library.name} {user.username}
      </h1>
      <hr className="mb-4" />
      <div className="d-flex flex-wrap" style={{ gap: "5px" }}>
        {library.books.map(book => (
          <Book key={book.isbn} book={book} />
        ))}
      </div>
    </Layout>
  );
}

export default HomePage;
