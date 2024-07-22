import axios from "axios";

export const RegisterPatient = async (data) => {
  const apiRoute = import.meta.env.VITE_API_ROUTE;
  const registerPatient = import.meta.env.VITE_REGISTER_PATIENT;
  try {
    const response = await axios.post(apiRoute + registerPatient, data);
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.log(error.response.data);
    return error.response.data;
  }
};
