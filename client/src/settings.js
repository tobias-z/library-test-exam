const BASE_URL = "http://localhost:8080/library/api";

const USER = {
  LOGIN: `${BASE_URL}/login`,
  VALIDATE_TOKEN: `${BASE_URL}/login/validate-token`,
};

const INFO = {
  USER: `${BASE_URL}/info/user`,
  ADMIN: `${BASE_URL}/info/admin`,
  FETCH_MANY: `${BASE_URL}/info/fetchMany`,
  FETCH_ONE: `${BASE_URL}/info/fetchData`,
};

const LIBRARY = {
  GET_LIBRARY: `${BASE_URL}/library`,
  DELETE_BOOK: isbn => `${BASE_URL}/library/${isbn}`,
};

const BOOKS = {
  LOAN: isbn => `${BASE_URL}/books/loan/${isbn}`,
  GET_ALL_FROM_USER: `${BASE_URL}/books`,
  EDIT_BOOK: `${BASE_URL}/books`,
};

export { USER, INFO, LIBRARY, BOOKS };
