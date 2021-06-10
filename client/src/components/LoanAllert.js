import * as React from "react";
import { Toast } from "react-bootstrap";
import { useAllert } from "../context/AllertProvider";

function LoanAllert() {
  const { loan } = useAllert();
  return (
    <Toast
      style={{
        position: "absolute",
        top: "40px",
        right: "40px",
        zIndex: "9999",
      }}>
      <Toast.Header>
        <strong className="mr-auto">{loan.book.title}</strong>
        <small>Checked out at: {loan.checkout}</small>
      </Toast.Header>
      <Toast.Body>
        Thank you for using our service. Please be back with the book at latest{" "}
        {loan.dueTo}
      </Toast.Body>
    </Toast>
  );
}

export default LoanAllert;
