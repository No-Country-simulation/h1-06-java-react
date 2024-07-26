import axios from 'axios';

export const GetAppointmentDoctor = async (user, date) => {
    const apiRoute = import.meta.env.VITE_API_ROUTE;
    const getAppointmentDoctor = import.meta.env.VITE_GET_APPOINTMENT_DOCTOR;
    
    const startDate = new Date(date).toISOString().split('T')[0] + 'T00:00';
    const endDate = new Date(date).toISOString().split('T')[0] + 'T23:59';

    try {
        const response = await axios.get(`${apiRoute}${getAppointmentDoctor}${user.id}/${startDate}/${endDate}/true`, {
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
}
