/* eslint-disable no-unused-vars */
import { useEffect, useState } from "react";
import { GetMedicalHistory } from "../../../../services/Patient/GetMedicalHistory";
import { useUserLogin } from "../../../../store/UserLogin";
import downloadIcon from "../../../../../public/assets/icons/descarga.svg";

function MedicalRecords({ ...props }) {
  const { user } = useUserLogin();
  const [record, setRecord] = useState([
    {
      fecha_del_tratamiento: "23 de enero, 2024",
      patologia: "Hipertensión",
      tratamiento: "87.65.00",
      medicamentos: [
        {
          nombre: "Losartan",
          dosaje: "50mg cada 24 horas",
        },
        {
          nombre: "Hidroclorotiazida",
          dosaje: "25mg cada 24 horas",
        },
        {
          nombre: "Amlodipino",
          dosaje: "10mg cada 24 horas",
        },
      ],
    },
    {
      fecha_del_tratamiento: "15 de julio, 2024",
      patologia: "Diabetes Tipo 2",
      tratamiento: "45.15.00",
      medicamentos: [
        {
          nombre: "Metformina",
          dosaje: "500mg cada 12 horas",
        },
        {
          nombre: "Glibenclamida",
          dosaje: "5mg cada 24 horas",
        },
        {
          nombre: "Insulina glargina",
          dosaje: "10 unidades cada 24 horas",
        },
      ],
    },
  ]);

  /*   useEffect(() => {
    const fetchData = async () => {
      const response = await GetMedicalHistory(user);
      setRecord(response);
      console.log(response);
    };
    fetchData();
  }, []); */

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
        {record.map((rec, index) => (
          <div key={index} className="record">
            <h2>{rec.fecha_del_tratamiento}</h2>
            <span>
              <strong>Patología:</strong> {rec.patologia}
            </span>
            <span>
              <strong>Tratamiento:</strong> {rec.tratamiento}
            </span>
            <div>
              <h3>Medicamentos</h3>
              <ul>
                {rec.medicamentos.map((medicamento, medIndex) => (
                  <>
                    <li key={medIndex}>
                      <p>{medicamento.nombre} - </p>
                      <p> {medicamento.dosaje}</p>
                    </li>
                  </>
                ))}
              </ul>
            </div>
            <div className="buttonContainer">
              <button>
                <img src={downloadIcon} />
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default MedicalRecords;
