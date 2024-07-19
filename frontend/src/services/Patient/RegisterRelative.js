import axios from "axios";

export const RegisterRelative = async (data) => {
  try {
    const response = await axios.post(
      "http://localhost:7082/api/v1/relative/register",
      data
    );
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.log(error.response.data);
    return error.response.data;
  }
};
