import axios from "axios";

export const RegisterDoctor = async (data) => {
  const apiRoute = import.meta.env.VITE_API_ROUTE;
  const registerDoctor = import.meta.env.VITE_REGISTER_DOCTOR;
  try {
    const response = await axios.post(apiRoute + registerDoctor, data);
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.log(error.response.data);
    return error.response.data;
  }
};