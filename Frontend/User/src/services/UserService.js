import axios from "axios";

const API = "http://localhost:8080/api/users";

export const registerUser = (user) => {
  return axios.post(`${API}/register`, user);
};

export const loginUser = (email, password) => {
  return axios.post(
    `${API}/login?email=${email}&password=${password}`
  );
};
export const getProfile = (email) => {
    return axios.get(`${API}/profile?email=${email}`);
};