import axios from 'axios';

export const GetAppointmentDoctorMonth = async (user) => {
    const apiRoute = import.meta.env.VITE_API_ROUTE;
    const getAppointmentDoctorMonth = import.meta.env.VITE_GET_APPOINTMENT_DOCTOR_MONTH;

    const currentDate = new Date();
    const year = currentDate.getFullYear();
    const month = currentDate.getMonth();

    const startDate = new Date(year, month, 1).toISOString().slice(0, 16);
    const endDate = new Date(year, month + 1, 0, 23, 59).toISOString().slice(0, 16); 

    try {
        const response = await axios.get(`${apiRoute}${getAppointmentDoctorMonth}${user.id}/${startDate}/${endDate}/true`, {
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
