import { Link } from "react-router-dom";
import editIcon from "../../../../../public/assets/icons/editIcon.svg";
import { useUserLogin } from "../../../../store/UserLogin";
import { useEffect } from "react";
import { GetPatientById } from "../../../../services/Patient/GetPatientById";

function PersonalData() {
  const { user,setPatientProfileData, patientProfileData } = useUserLogin();

  useEffect(() => {
    const fetchPatientProfileData = async () => {
      try {
        const currentPatientProfileData = await GetPatientById(user);
        setPatientProfileData(currentPatientProfileData);
      } catch (error) {
        console.error('Error fetching patient profile data:', error);
      }
    };

    fetchPatientProfileData();
  }, []);
  console.log("user:", user);
  return (
    <div id="personal-data">
      <div id="personal-data-container" className="flex-column">
        <div id="personal-data-header" className="flex-row">
          <div id="personal-data-title">
            <h2>Datos personales</h2>
          </div>
          <Link
            to={"/patient/profile/edit-personal-data"}
            id="personal-data-edit"
          >
            <img src={editIcon} />
          </Link>
        </div>
        <div id="personal-data-content" className="flex-column">
          <div className="flex-column">
            <span className="personalDataTitle">Nombre completo</span>
            <span className="personalDataContent">
              {patientProfileData.name} {patientProfileData.surname}
            </span>
          </div>
          <div className="flex-column">
            <span className="personalDataTitle">Fecha de nacimiento</span>
            <span className="personalDataContent">{patientProfileData.dateOfBirth}</span>
          </div>
          <div className="flex-column">
            <span className="personalDataTitle">Sexo</span>
            <span className="personalDataContent">{patientProfileData.gender}</span>
          </div>
          <div className="flex-column">
            <span className="personalDataTitle">Email</span>
            <span className="personalDataContent">{patientProfileData.email}</span>
          </div>
        </div>
      </div>
    </div>
  );
}

export default PersonalData;
