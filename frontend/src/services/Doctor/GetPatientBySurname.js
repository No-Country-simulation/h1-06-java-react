import axios from 'axios';

export const GetPatientBySurname = async (user, surname) => {
    const apiRoute = import.meta.env.VITE_API_ROUTE;
    const getPatientBySurname = import.meta.env.VITE_GET_PATIENT_BY_SURNAME;

    try {
        const response = await axios.get(`${apiRoute}${getPatientBySurname}${surname}/true`, {
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
