import axios from "axios";

export const GetAppointment = async (user) => {
  const apiRoute = import.meta.env.VITE_API_ROUTE;
  const getAppointment = import.meta.env.VITE_GET_APPOINTMENT_PATIENT;
  try {
    const response = await axios.get(
      apiRoute + getAppointment + user.id + "/true",
      {
        headers: {
          Authorization: `Bearer ${user.jwt}`,
        },
      }
    );
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.log(error.response.data);
    return error.response.data;
  }
};
