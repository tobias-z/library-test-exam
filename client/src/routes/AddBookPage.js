import * as React from "react";
import { Button, Form } from "react-bootstrap";
import { fetchData, handleError, https } from "../apiUtils";
import { WithAdmin } from "../components/Book";
import Layout from "../components/Layout";
import MyInput from "../components/MyInput";
import { useLibrary } from "../context/LibraryProvider";
import useForm from "../hooks/useForm";
import { LIBRARY } from "../settings";

const initialValues = {
  title: "",
  publisher: "",
  publishYear: "",
  description: "",
  image: "",
  authur: "",
};

function AddBookPage() {
  const { handleChange, resetForm, values, setValues } = useForm(initialValues);
  const [authors, setAuthors] = React.useState([]);
  const { findLibrary } = useLibrary();
  const [error, setError] = React.useState(null);
  const [isSuccess, setIsSuccess] = React.useState(false);

  async function handleSubmit(e) {
    e.preventDefault();
    const body = {
      ...values,
      authors,
    };
    try {
      await fetchData(LIBRARY.ADD_BOOK, https.POST, body);
      await findLibrary();
      setIsSuccess(true);
      resetForm();
    } catch (err) {
      handleError(err, setError);
    }
  }

  return (
    <Layout>
      <h1 className="text-center">Fill in filds to add a book</h1>
      <hr className="mb-4" />
      <WithAdmin>
        {error && <h3 className="text-danger">{error.message}</h3>}
        {isSuccess && (
          <h3 className="text-success">Book was added to the library!</h3>
        )}
        <Form onSubmit={handleSubmit}>
          <MyInput
            id="title"
            value={values.title}
            onChange={handleChange}
            name="title"
            label="Title"
            placeholder="Enter Title"
          />
          <div>
            <MyInput
              id="authur"
              value={values.authur}
              onChange={handleChange}
              name="authur"
              label="Author"
              placeholder="Enter author to add"
            />
            <Button
              onClick={() => {
                setAuthors([...authors, values.authur]);
                setValues({
                  ...values,
                  authur: "",
                });
              }}
              size="sm"
              variant="secondary">
              Add authur
            </Button>
            <ul className="mt-1">
              {authors.map(authur => (
                <li key={authur}>{authur}</li>
              ))}
            </ul>
          </div>
          <MyInput
            id="publisher"
            value={values.publisher}
            onChange={handleChange}
            name="publisher"
            label="Publisher"
            placeholder="Enter Publisher"
          />
          <MyInput
            id="publishYear"
            value={values.publishYear}
            onChange={handleChange}
            name="publishYear"
            label="Publish Year"
            placeholder="Enter Publish Year"
          />
          <MyInput
            id="description"
            value={values.description}
            onChange={handleChange}
            name="description"
            label="Description"
            placeholder="Enter Description"
          />
          <MyInput
            id="image"
            value={values.image}
            onChange={handleChange}
            name="image"
            label="Image"
            placeholder="Enter link to image"
          />
          <Button block size="lg" type="submit">
            Add Book
          </Button>
        </Form>
      </WithAdmin>
    </Layout>
  );
}

export default AddBookPage;
