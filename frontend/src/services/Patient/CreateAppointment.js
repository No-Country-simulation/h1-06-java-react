import axios from "axios";

export const CreateAppointment = async (data, user) => {
    const apiRoute = import.meta.env.VITE_API_ROUTE;
    const createAppointment = import.meta.env.VITE_CREATE_APPOINTMENT_PATIENT;
    try {
        const response = await axios.post(
            apiRoute + createAppointment,
            data,
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
}