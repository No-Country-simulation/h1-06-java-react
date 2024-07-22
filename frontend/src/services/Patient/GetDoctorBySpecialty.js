import axios from "axios";

export const GetDoctorBySpecialty = async (specialty, user) => {
  const apiRoute = import.meta.env.VITE_API_ROUTE;
  const getSpecialtiesByDoctor = import.meta.env.VITE_GET_SPECIALTIES_BY_DOCTOR;
  try {
    const response = await axios.get(apiRoute + getSpecialtiesByDoctor + specialty,{
        headers: {
            Authorization: `Bearer ${user.jwt}`,
        },
    });
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.log(error.response.data);
    return error.response.data;
  }
};
