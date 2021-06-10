import * as React from "react";

function useForm(initialValues) {
  const [values, setValues] = React.useState(initialValues);

  function handleChange(e) {
    setValues({
      ...values,
      [e.target.name]: e.target.value,
    });
  }

  const resetForm = () => setValues(initialValues);

  return {
    values,
    setValues,
    handleChange,
    resetForm,
  };
}

export default useForm;
