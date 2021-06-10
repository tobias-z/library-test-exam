import { fetchData, https } from "../apiUtils";
import { USER, INFO, BOOKS } from "../settings";

async function login(credentials) {
  const user = await fetchData(USER.LOGIN, https.POST, credentials);
  setToken(user.token);
  const loans = await fetchData(BOOKS.GET_ALL_FROM_USER);
  return [user, loans];
}

function getDataFromServer() {
  return fetchData(INFO.USER);
}

const setToken = token => localStorage.setItem("jwtToken", token);
const getToken = () => localStorage.getItem("jwtToken");
const isLoggedIn = () => getToken() != null;
const logout = () => localStorage.removeItem("jwtToken");

export { setToken, getToken, isLoggedIn, login, logout, getDataFromServer };
