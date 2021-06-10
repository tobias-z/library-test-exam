import * as React from "react";
import { fetchData, handleError } from "../apiUtils";
import { LIBRARY } from "../settings";

const LibraryContext = React.createContext({
  library: null,
  error: "",
  findLibrary: async () => {},
});

function useLibrary() {
  const context = React.useContext(LibraryContext);
  if (!context) throw Error("Usage of useLibrary outside of it's provider");
  return context;
}

function LibraryProvider({ children }) {
  const [library, setLibrary] = React.useState(null);
  const [error, setError] = React.useState(null);

  async function findLibrary() {
    try {
      const lib = await fetchData(LIBRARY.GET_LIBRARY);
      setLibrary(lib);
    } catch (err) {
      handleError(err, setError);
    }
  }

  React.useEffect(() => {
    findLibrary();
  }, []);

  const values = {
    library,
    error,
    findLibrary,
  };

  return (
    <LibraryContext.Provider value={values}>{children}</LibraryContext.Provider>
  );
}

export { LibraryProvider, useLibrary };
