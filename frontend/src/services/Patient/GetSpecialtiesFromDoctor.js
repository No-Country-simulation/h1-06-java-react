import axios from "axios";

export const GetSpecialtiesFromDoctor = async () => {
  const apiRoute = import.meta.env.VITE_API_ROUTE;
  const getSpecialties = import.meta.env.VITE_GET_SPECIALTIES_PATIENT;
  try {
    const response = await axios.get(apiRoute + getSpecialties);
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.log(error.response.data);
    return error.response.data;
  }
};
