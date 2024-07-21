import axios from "axios";

export const RegisterPatient = async (data) => {
  try {
    const response = await axios.post(
      "http://localhost:7082/api/v1/patient/register",
      data
    );
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.log(error.response.data);
    return error.response.data;
  }
};
