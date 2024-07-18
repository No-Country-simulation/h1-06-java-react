import axios from "axios";

export const Login = async (email, password) => {
  try {
    const response = await axios.post("http://localhost:7082/api/v1/login", {
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
