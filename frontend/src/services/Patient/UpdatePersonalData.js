import axios from "axios";

export const UpdatePersonalData = async (data, user) => {
    const apiRoute = import.meta.env.VITE_API_ROUTE;
    const updatePersonalData = import.meta.env.VITE_UPDATE_PERSONAL_DATA_PATIENT;
    try {
        const response = await axios.put(
            apiRoute + updatePersonalData,
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