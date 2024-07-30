import axios from "axios";
const downloadTreatment = async (id, user) => {
  const apiRoute = import.meta.env.VITE_API_ROUTE;
  const getTreatment = import.meta.env.VITE_GET_MEDICAL_HISTORY_PATIENT;
  try {
    const response = await axios.get(
      apiRoute + getTreatment + id + "/medicalRecordPdf",
      {
          headers: {
              Authorization: `Bearer ${user.jwt}`,
          },
      }
    );
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.log(error.response);
    return error.response.data;
  }
};

export default downloadTreatment;
