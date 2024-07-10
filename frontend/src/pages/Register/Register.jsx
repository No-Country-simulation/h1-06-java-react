import { useState } from "react";
import PatientForm from "../patient/PatientForm";
import "./Register.css";

function Register() {
  const [profileSelection, setProfileSelection] = useState(undefined);
  const [registerForm, setRegisterForm] = useState({
    name: "",
    email: "",
    lastName: "",
    dni: "",
    birthDate: "",
    gender: "",
    password: "",
    bloodType: "",
    confirmPersonalData: false,
    tutor: false,
  });
  return (
    <div id="register">
      <div id="register-container" className="interFont">
        <div id="register-title">
          <h1 className="just-another-hand-regular">Regístro</h1>
        </div>
        <div id="profile-selection">
          <div>
            <label>Seleccione su perfil</label>
            <ul>
              <select className="inputLayout">
                <option className="interFont">Selecciones su perfil</option>
                <option
                  value="paciente"
                  onClick={() => setProfileSelection("paciente")}
                >
                  Paciente
                </option>
                <option
                  value="familiar-paciente"
                  onClick={() => setProfileSelection("familiar-paciente")}
                >
                  Familiar Paciente
                </option>
                <option
                  value="medico"
                  onClick={() => setProfileSelection("medico")}
                >
                  Medico
                </option>
                <option
                  value="centro-transplante"
                  onClick={() => setProfileSelection("centro-transplante")}
                >
                  Centro de Transplante
                </option>
                <option
                  value="terapia-intensiva"
                  onClick={() => setProfileSelection("terapia-intensiva")}
                >
                  Terapia Intensiva
                </option>
                <option
                  value="asiociacion"
                  onClick={() => setProfileSelection("asiociacion")}
                >
                  Asociacion/ Fundacion/ ONG
                </option>
                <option
                  value="incucai"
                  onClick={() => setProfileSelection("incucai")}
                >
                  INCUCAI
                </option>
                <option
                  value="casa-justina"
                  onClick={() => setProfileSelection("casa-justina")}
                >
                  Casa Justina
                </option>
                <option
                  value="farmacia"
                  onClick={() => setProfileSelection("farmacia")}
                >
                  Farmacia
                </option>
              </select>
            </ul>
          </div>
        </div>
        <PatientForm
          profileSelection={profileSelection}
          registerForm={registerForm}
          setRegisterForm={setRegisterForm}
        />
        {/*profileSelection === "patient" ? <PatientForm profileSelection={profileSelection} /> : "" : profileSelection === "doctor" ? <DoctorForm profileSelection={profileSelection} /> : ""}  */}
      </div>
    </div>
  );
}

export default Register;
