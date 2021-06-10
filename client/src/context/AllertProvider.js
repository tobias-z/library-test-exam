import * as React from "react";
import LoanAllert from "../components/LoanAllert";

const AllertContext = React.createContext(null);

const useAllert = () => React.useContext(AllertContext);

function AllertProvider({ children }) {
  const [loan, setLoan] = React.useState(null);

  function showLoan(loan) {
    setLoan(loan);
    setTimeout(() => setLoan(null), 8000);
  }

  const values = {
    loan,
    showLoan,
  };

  return (
    <AllertContext.Provider value={values}>
      {loan ? (
        <>
          <LoanAllert />
          {children}
        </>
      ) : (
        <>{children}</>
      )}
    </AllertContext.Provider>
  );
}

export { AllertProvider, useAllert };
