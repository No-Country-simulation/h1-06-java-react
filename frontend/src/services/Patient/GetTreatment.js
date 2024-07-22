import axios from "axios";

const GetTreatment = async (user) => {
    const apiRoute = import.meta.env.VITE_API_ROUTE;
    const getTreatment = import.meta.env.VITE_GET_TREATMENT_PATIENT;
    try {
        const response = await axios.get(
            apiRoute + getTreatment + user.id,
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

export default GetTreatment;