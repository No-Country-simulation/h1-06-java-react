import axios from "axios";

export const GetMedicalHistory = async (user) => {
    const apiRoute = import.meta.env.VITE_API_ROUTE;
    const getMedicalHistory = import.meta.env.VITE_GET_MEDICAL_HISTORY_PATIENT;
    try {
        const response = await axios.get(
            apiRoute + getMedicalHistory + user.id,
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