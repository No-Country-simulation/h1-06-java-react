import axios from "axios";

const DeletePatient = async (user) => {
    const apiRoute = import.meta.env.VITE_API_ROUTE;
    const deletePatient = import.meta.env.VITE_DELETE_PATIENT;
    try {
        const response = await axios.delete(apiRoute + deletePatient + user.id, {
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
export default DeletePatient