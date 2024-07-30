/* eslint-disable no-unused-vars */
import { useEffect, useState } from "react";
import { GetMedicalHistory } from "../../../../services/Patient/GetMedicalHistory";
import { useUserLogin } from "../../../../store/UserLogin";
import downloadIcon from "../../../../../public/assets/icons/download.svg";
import downloadTreatment from "../../../../services/Patient/DownloadTreatment";

function MedicalRecords({ ...props }) {
  const { user } = useUserLogin();
  const [record, setRecord] = useState([]);
  const [recordByDate, setRecordByDate] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const response = await GetMedicalHistory(user);
      setRecord(response);
      console.log(response);
    };
    fetchData();
  }, []);

  const downloadHandler = (id) => {
    downloadTreatment(id, user);
  };

  console.log("RECORDDD", record);
  return (
    <div id="medicalRecords">
      <div id="medicalRecords-container">
        {record?.length > 0 ? (
          record?.map((rec, index) => (
            <>
              <div key={rec.id} className="record">
                <h2>{new Date(rec.date).toLocaleDateString()}</h2>
                <span>
                  <strong>Patología:</strong>{" "}
                  {rec.pathologyList
                    .map((pathology) => pathology.name)
                    .join(", ")}
                </span>
                <span>
                  <strong>Tratamiento:</strong> {rec.medicalProcedureCode}
                </span>
                <div>
                  <h3>Medicamentos:</h3>
                  <ul className="medicineList">
                    {rec.medicineList &&
                      rec.medicineList.map((medicine, index) => (
                        <li key={index}>
                          <p>{medicine.name}</p>
                        </li>
                      ))}
                    <p>{rec.frequency}</p>
                  </ul>
                </div>
              </div>
              <div className="buttonContainer">
                <button
                  className="buttonContainer"
                  onClick={() => downloadHandler(rec.id)}
                >
                  <span>Descargar</span>
                  <img
                    src={downloadIcon}
                    alt="Download"
                    className="bg-transparent"
                  />
                </button>
              </div>
            </>
          ))
        ) : (
          <div className="flex-column fullScreen">
            No se han encontrado historias clínicas
          </div>
        )}
      </div>
    </div>
  );
}

export default MedicalRecords;
