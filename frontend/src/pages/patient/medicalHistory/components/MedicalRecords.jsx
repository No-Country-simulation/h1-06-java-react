/* eslint-disable no-unused-vars */
import { useEffect, useState } from "react";
import { GetMedicalHistory } from "../../../../services/Patient/GetMedicalHistory";
import { useUserLogin } from "../../../../store/UserLogin";
import downloadIcon from "../../../../../public/assets/icons/download.svg";

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

  useEffect(() => {
    const fetchData = async () => {
      const response = await GetMedicalHistory();
      setRecord(response);
    };
    fetchData();
  }, []);
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
                  <h3>Medicamentos</h3>
                  <ul>
                    {record.medicineList?.map((medicine, medIndex) => (
                      <li key={medicine.id}>
                        <p>{medicine.name} - </p>
                        <p>{record.frequency}</p>
                      </li>
                    ))}
                  </ul>
                </div>
              </div>
              <div className="buttonContainer">
                <button className="buttonContainer">
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
