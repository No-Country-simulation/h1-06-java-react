import axios from "axios";


export const GetDoctorById = async (user) => {
    const apiRoute = import.meta.env.VITE_API_ROUTE;
    const getDoctorById = import.meta.env.VITE_GET_DOCTOR_BY_ID;
    try {
        const response = await axios.get(apiRoute + getDoctorById + user.id + "/true", {
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