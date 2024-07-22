/* eslint-disable no-undef */
import axios from "axios";

export const Login = async (email, password) => {
  const apiRoute = import.meta.env.VITE_API_ROUTE;
  const loginRoute = import.meta.env.VITE_API_LOGIN;
  try {
    const response = await axios.post(apiRoute + loginRoute, {
      email,
      password,
    });
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.log(error.response.data);
    return error.response.data;
  }
};
