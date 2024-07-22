import axios from "axios";

export const RegisterRelative = async (data) => {
  const apiRoute = import.meta.env.VITE_API_ROUTE;
  const registerRelative = import.meta.env.VITE_REGISTER_RELATIVE;
  try {
    const response = await axios.post(apiRoute + registerRelative, data);
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.log(error.response.data);
    return error.response.data;
  }
};
