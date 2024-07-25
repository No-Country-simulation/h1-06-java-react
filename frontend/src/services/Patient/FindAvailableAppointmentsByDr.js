import axios from "axios";
import { getFormattedDate } from "../../hooks/getFormattedDate";

export const FindAvailableAppointmentsByDr = async (
  doctor,
  user,
  selectedDate
) => {
  const apiRoute = import.meta.env.VITE_API_ROUTE;
  const findAvailableAppointmentsByDr = import.meta.env
    .VITE_FIND_AVAILABLE_APPOINTMENTS_BY_DR;
    const currentDate = getFormattedDate(new Date());
  try {
    const response = await axios.get(
      apiRoute +
        findAvailableAppointmentsByDr +
        doctor.id / +currentDate / +selectedDate,
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
