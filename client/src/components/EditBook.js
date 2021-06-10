import * as React from "react";
import { Button, Form, Modal } from "react-bootstrap";
import { fetchData, handleError, https } from "../apiUtils";
import { useLibrary } from "../context/LibraryProvider";
import useForm from "../hooks/useForm";
import { BOOKS } from "../settings";
import MyInput from "./MyInput";

function EditBook({ book }) {
  const [showEditModal, setShowEditModal] = React.useState(false);
  const initialValues = {
    title: book.title,
    publisher: book.publisher,
    publishYear: book.publishYear,
    description: book.description,
    image: book.image,
  };
  const { handleChange, values, resetForm } = useForm(initialValues);
  const { findLibrary } = useLibrary();
  const [error, setError] = React.useState(null);
  const [success, setSuccess] = React.useState(false);

  const handleClose = () => {
    resetForm();
    setShowEditModal(false);
  };
  const handleShow = () => setShowEditModal(true);

  async function handleEditBook(params) {
    let body = {
      ...values,
      isbn: book.isbn,
      authors: book.authors,
    };
    console.log(body);
    try {
      await fetchData(BOOKS.EDIT_BOOK, https.PUT, body);
      setSuccess(true);
      await findLibrary();
    } catch (error) {
      handleError(error, setError);
    }
  }

  function handleSubmit(e) {
    e.preventDefault();
    console.log(values);
  }

  return (
    <>
      <Button variant="secondary" onClick={handleShow}>
        Edit
      </Button>

      <Modal show={showEditModal} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Editing {book.title}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {success && (
            <h5 className="text-success my-3">Edit was succesfull</h5>
          )}
          {error && <h5 className="text-success my-3">{error.message}</h5>}
          <Form onSubmit={handleSubmit}>
            <MyInput
              id="title"
              value={values.title}
              onChange={handleChange}
              name="title"
              label="Title"
              placeholder="Enter Title"
            />
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
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleEditBook}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default EditBook;
